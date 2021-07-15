import threading as th

def printA():
    print("A")

def printB():
    print("B")

def main():
    p1 = th.Thread(target=printA)
    p2 = th.Thread(target=printB)
    p1.start()
    p2.start()

if __name__ == "__main__":
    main()