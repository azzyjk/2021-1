from google.cloud import vision
import io
import numpy as np
import cv2
import time
from rpi_ws281x import *
import argparse
import threading as th
import smtplib 
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.image import MIMEImage
from gpiozero import Button
import pymysql
import datetime

# LED strip configuration:
LED_COUNT      = 1      # Number of LED pixels.
LED_PIN        = 18      # GPIO pin connected to the pixels (18 uses PWM!).
#LED_PIN        = 10      # GPIO pin connected to the pixels (10 uses SPI /dev/spidev0.0).
LED_FREQ_HZ    = 800000  # LED signal frequency in hertz (usually 800khz)
LED_DMA        = 10      # DMA channel to use for generating signal (try 10)
LED_BRIGHTNESS = 255     # Set to 0 for darkest and 255 for brightest
LED_INVERT     = False   # True to invert the signal (when using NPN transistor level shift)
LED_CHANNEL    = 0       # set to '1' for GPIOs 13, 19, 41, 45 or 53

cap = cv2.VideoCapture(0)
cap.set(3, 1280)
cap.set(4, 960)


strip = Adafruit_NeoPixel(LED_COUNT, LED_PIN, LED_FREQ_HZ, LED_DMA, LED_INVERT, LED_BRIGHTNESS, LED_CHANNEL)
strip.begin()
button = Button(2)
strip.setPixelColor(0, Color(100, 100, 100))
strip.show()

likelihood_name = ('UNKNOWN','VERY_UNLIKELY','UNLIKELY','POSSIBLE', 'LIKELY','VERY_LIKELY')

# mail info
sendMail = False
userID = "2021indust@gmail.com"
userPasswd = ""
userAppPasswd = ""
receiveID = "2021indust@gmail.com"
imgPath = ""

smtp = smtplib.SMTP("smtp.gmail.com", 587) # SMTP instance
smtp.starttls() # TLS mode
smtp.login(userID, userAppPasswd)

# lock
apiLock = False
detect = False

def insertDb(kinds, level,path):
    conn = pymysql.connect(host='localhost',user='ia',password='ia',db='ia')
    curs = conn.cursor(pymysql.cursors.DictCursor)
    now = datetime.datetime.now()
    nowDatetime = now.strftime('%y-%m-%d %H:%M:%S')
    sql = "insert into log (datetime,kinds,level,path) values(%s,%s,%s,%s)"
    val = (now,kinds,level,path)
    curs.execute(sql,val)
    conn.commit() 
    curs.close()
    conn.close()


def detect_safe_search(path, name):
    global detect, sendMail, imgPath
    # global sendMail

    client = vision.ImageAnnotatorClient()
    with io.open(path,'rb') as image_file:
        content = image_file.read()
    image = vision.Image(content=content)
    response = client.safe_search_detection(image=image)
    safe = response.safe_search_annotation


    print('Safe search:')
    print('violence: {}'.format(likelihood_name[safe.violence]))
    
    if safe.violence > 2:
        if detect == False:
            detect = True
            imgPath = path
            sendMail = True
            strip.setPixelColor(0, Color(255, 0, 0))
            strip.show()
            insertDb(3,int(safe.violence),str(name)+".jpg")
            button.wait_for_press()
            print("button")
            strip.setPixelColor(0, Color(100, 100, 100))
            strip.show()
            detect = False
    

def apiLock_change_loop():
    global apiLock
    while True:
        apiLock = not apiLock
        time.sleep(1)

def sendToMail():
    global sendMail
    while True:
        if sendMail == True:
            time.sleep(5)
            sendMail = False
            data = MIMEMultipart()
            data['From'] = userID
            data['To'] = userID
            data['Subject'] = "Warning!!"
            print(f"ImgPath : {imgPath}")
            with open(imgPath, 'rb') as fp:
                img = MIMEImage(fp.read(), Name="capture.png")
                img.add_header('Content-ID', '<capture>')
                data.attach(img)

            
            msg = MIMEText("Hello test <br /><img src='cid:capture'>", 'html')
            data.attach(msg)
            # msg['Subject'] = 'title : Violence was detected.'
            smtp.sendmail(userID, receiveID, data.as_string())
        time.sleep(1)

def main():
    global apiLock
    i = 1
    apiLock_change_th = th.Thread(target=apiLock_change_loop)
    apiLock_change_th.start()

    sendToMail_th = th.Thread(target=sendToMail)
    sendToMail_th.start()
    while(True):
        ret, frame = cap.read()
        frame = cv2.flip(frame, -1)
        
        cv2.imshow('frame', frame)
        
        k = cv2.waitKey(30) & 0xff
        if k == 27:
            strip.setPixelColor(0, Color(0, 0, 0))
            strip.show()
            break
        
        file_name = '/home/pi/Desktop/nodejs/public/photo/' + str(i) + '.jpg'
        cv2.imwrite(file_name,frame)
        

        # detect_safe_search(file_name)
        
        if apiLock == True and detect == False:
            detect_thread = th.Thread(target=detect_safe_search, args=(file_name, i))
            detect_thread.start()

        i = i + 1

    strip.setPixelColor(0, Color(0, 0, 0))

    cap.release()
    cv2.destroyAllWindows()
    
    smtp.quit()

if __name__ == "__main__":
    main()