import java.io.File
import java.util.*

class ClientManager {
    val clients = mutableMapOf<String, Client>()
    init{
        val scan = Scanner(File("client.txt"))
        while(scan.hasNext()){
            val str = scan.nextLine()
            var line = str.split(" ")
            clients[line[0]] = Client(line[0], line[1], line[2], line[3], line[4].toInt(), line[5])
        }
    }

    fun findClient(id:String) = clients[id]
    fun getPoint(c:Client) = c.point
    fun getTel(c:Client) = c.tel
    fun getName(c:Client) = c.name
    fun getPasswd(c:Client) = c.passwd
    fun getAnswer(c:Client) = c.answer

    fun findID(tel:String, name:String):String? {
        val res = clients.filterValues{it.tel == tel && it.name == name}.keys
        if(res.isEmpty() ) println("There is no ID for that phone number or Name. Check your phone number or Name.")
        else return res.iterator().next()
        return null
    }
}