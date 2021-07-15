import threading as th
import smtplib 
from email.mime.text import MIMEText

def printA():
    print("A")

def printB():
    print("B")

def mailSend(receiveID):
    userID = "2021indust@gmail.com"
    userAppPasswd = ""

    smtp = smtplib.SMTP('smtp.gmail.com', 587) # SMTP instance
    smtp.starttls() # TLS mode
    smtp.login(userID, userAppPasswd) # login

    msg = MIMEText('Content : Content Test')
    msg['Subject'] = 'title : Send mail test'

    smtp.sendmail(userID, receiveID, msg.as_string())

    smtp.quit()

def main():
    mailThread = th.Thread(target=mailSend, args=("2021indust@gmail.com",))
    mailThread.start()

if __name__ == "__main__":
    main()