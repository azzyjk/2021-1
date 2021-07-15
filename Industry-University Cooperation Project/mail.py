import smtplib 
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.image import MIMEImage

userID = "2021indust@gmail.com"
userPasswd = ""
userAppPasswd = ""
receiveID = "2021indust@gmail.com"
imageName = "/Users/jjw/Desktop/Code/2021-1/Industry-University Cooperation Project/welsh.jpg"

smtp = smtplib.SMTP('smtp.gmail.com', 587) # SMTP instance
smtp.starttls() # TLS mode
smtp.login(userID, userAppPasswd) # login

data = MIMEMultipart()
data['From'] = userID
data['To'] = userID
data['Subject'] = "Title : Send mail test"

with open(imageName, 'rb') as fp:
    img = MIMEImage(fp.read(), Name = "capture.png")
    img.add_header('Content-ID', '<capture>')
    data.attach(img)



msg = MIMEText("Hello test <br /><img src='cid:capture'>", 'html')
data.attach(msg)
print(data)

smtp.sendmail(userID, receiveID, data.as_string())

smtp.quit()
