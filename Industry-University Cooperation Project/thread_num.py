import threading as th
import time

thread_num = 0
THREAD_MAX = 980

def waitProc(num):
    global thread_num
    time.sleep(3)
    print(f"I'm {num} bye")
    thread_num -= 1

def main():
    global thread_num
    i = 1
    while(1):
        if thread_num <= THREAD_MAX:
            thread_num +=1
            th.Thread(target=waitProc, args=(i,)).start()
            i+=1

if __name__ == "__main__":
    main()