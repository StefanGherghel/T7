import java.io.File
import java.io.InputStream
import java.sql.Time
import java.sql.Timestamp

fun main(args: Array<String>) {
    val inputStream: InputStream = File("history.log").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }



    val data_regex = Regex("\\d{4}-\\d{2}-\\d{2}\\s{2}\\d{2}:\\d{2}:\\d{2}");
    val command_regex = Regex("Commandline:((.*)*)")

    var lista_timestamp = mutableListOf<Timestamp>()
    var lista_comenzi = mutableListOf<String>()
    var lista_obiecte_History = mutableListOf<HistoryLogRecord>()

    lineList.forEach(){
        var data_si_ora = data_regex.find(it)?.value.toString()

        if(data_si_ora != "null")
        {
            var data_si_ora = data_si_ora.trim().replace("\\s+".toRegex(), " ")
            var timestamp = Timestamp.valueOf(data_si_ora)

            lista_timestamp.add(timestamp)
        }

        var command = command_regex.find(it)?.value.toString().split(":")
        var dim = command.size
        var comanda = command[dim-1]
        if(comanda != "null")
        {
            lista_comenzi.add(comanda)
        }






    }

    val history_log = mutableMapOf<Timestamp, HistoryLogRecord>()
    var index: Int = 0
    lista_comenzi.forEach(){
        var obj_log = HistoryLogRecord(lista_timestamp[index], it)
        lista_obiecte_History.add(obj_log)
        history_log.put(lista_timestamp[index], obj_log)
        index=index+2
    }

    var obj: HistoryLogRecord = maxim(lista_obiecte_History[3], lista_obiecte_History[4]);

    println("Am comparat:" + lista_obiecte_History[3].time_stamp.toString() + lista_obiecte_History[3].command)
    println("Am comparat:" + lista_obiecte_History[4].time_stamp.toString() + lista_obiecte_History[4].command)
    println("Maximul este "  + obj.time_stamp.toString() + "->" + obj.command)


    var my_map = mapa(history_log)
    println("INAINTE")

    my_map.elements.forEach { println(it.key.toString() + it.value.command)}
    println("+++++++++++++++++++++++")

    change(lista_obiecte_History[0], lista_obiecte_History[1], my_map)
    println("DUPA")
    history_log.forEach { println(it.key.toString() + it.value.command)}





}