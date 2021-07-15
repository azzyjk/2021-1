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

# LED strip configuration:
LED_COUNT      = 3      # Number of LED pixels.
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


def detect_safe_search(path):
    client = vision.ImageAnnotatorClient()
    with io.open(path,'rb') as image_file:
        content = image_file.read()
    image = vision.Image(content=content)
    response = client.safe_search_detection(image=image)
    safe = response.safe_search_annotation

    likelihood_name = ('UNKNOWN','VERY_UNLIKELY','UNLIKELY','POSSIBLE',
            'LIKELY','VERY_LIKELY')
    print('Safe search:')
    print('violence: {}'.format(likelihood_name[safe.violence]))
    
    if safe.violence < 3:
        strip.setPixelColor(0, Color(255, 255, 255))
    if safe.violence ==  3:
        strip.setPixelColor(0, Color(0, 0, 255))
    if safe.violence ==  4:
        strip.setPixelColor(0, Color(0, 255, 0))
    if safe.violence ==  5:
        strip.setPixelColor(0, Color(255, 0, 0))
    strip.show()




def main():
    i = 1
    while(True):
        ret, frame = cap.read()
        frame = cv2.flip(frame, -1)
        
        cv2.imshow('frame', frame)
        
        k = cv2.waitKey(30) & 0xff
        if k == 27:
            break
        
        file_name = './photo/' + str(i) + '.jpg'
        cv2.imwrite(file_name,frame)

        # detect_safe_search(file_name)
        i = i + 1
        if i%60 == 0:
            detect_thread = th.Thread(target=detect_safe_search, args=(file_name,))
            detect_thread.start()

    strip.setPixelColor(0, Color(0, 0, 0))

    cap.release()
    cv2.destroyAllWindows()
    

if __name__ == "__main__":
    main()