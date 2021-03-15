import java.lang.NumberFormatException

fun inputID():String{
    var tmp:String? = null
    print("Input your ID : ")
    tmp = readLine()!!
    return tmp
}

fun inputPasswd():String{
    var tmp:String? = null
    print("Input your Password : ")
    tmp = readLine()!!
    return tmp
}

fun inputTel():String{
    var tmp:String? = null
    print("Input your phone number : ")
    tmp = readLine()!!
    return tmp
}

fun inputName():String{
    var tmp:String? = null
    print("Input your name : ")
    tmp = readLine()!!
    return tmp
}

fun inputAnswer():String{
    var tmp:String? = null
    print("Your favorite animal : ")
    tmp = readLine()!!
    return tmp
}

fun main(){
    println("201711425 정준원")

    var select:Int = 0
    var id:String? = null
    var name:String? = null
    var tel:String? = null
    var passwd:String? = null
    var answer:String? = null
    var client:Client? = null
    val manager = ClientManager()

    while(select != 5){
        println("1) ID 2) passwd 3) phone number 4) point 5) exit")
        print("Select : ")

        try{
            select = readLine()!!.toInt()
        }catch(e : NumberFormatException){
            println("Check your input!!")
            continue
        }
        if(select==5) continue
        when(select){
            1->{
                tel = inputTel()
                name = inputName()
                id = manager.findID(tel!!, name)
                if(id!=null) println("Your ID : ${id}")
            }
            2->{
                id = inputID()
                answer = inputAnswer()
                client = manager.findClient(id!!)
                if(client == null) println("Check your ID")
                else {
                    if(manager.getAnswer(client) == answer) println("your password : ${manager.getPasswd(client)}")
                    else println("Answer is wrong. Check your favorite animal")
                }
            }
            3->{
                id = inputID()
                passwd = inputPasswd()
                client = manager.findClient(id!!)
                if(client == null) println("Check your ID")
                else {
                    if(manager.getPasswd(client) == passwd) println("your phone number : ${manager.getTel(client)}")
                    else println("Password is wrong. Check your password")
                }
            }
            4->{
                id = inputID()
                passwd = inputPasswd()
                client = manager.findClient(id!!)
                if(client == null) println("Check your ID")
                else {
                    if(manager.getPasswd(client) == passwd) println("you have ${manager.getPoint(client)} points.")
                    else println("Password is wrong. Check your password")
                }
            }
            else -> println("Check your select number")
        }
    }
}