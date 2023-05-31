import java.sql.Timestamp

class HistoryLogRecord(val time_stamp: Timestamp, val command: String): Comparable<HistoryLogRecord> {


    override fun compareTo(other: HistoryLogRecord): Int {
        if(this.time_stamp.compareTo(other.time_stamp)==1)
        {
            return 1;
        }
        return 0;
    }
}


//am creat o functie polimorfica care astepta ca parametrii doar clase care sunt subclase ale lui Comparable
fun <T : Comparable<T>>maxim(first: T, second:T) : T{
    val k = first.compareTo(second)
    return if (k>0) first else second
}


class mapa<T>(val elements: MutableMap<Timestamp, T>)
{
    fun add(str: Timestamp, t:T) = elements.put(str, t)

}


//nu ar trebui contravarianta?????
fun <T: HistoryLogRecord>change(old: T, new: T, my_map: MutableMap<Timestamp, in T>)
{
    my_map.replace(old.time_stamp, old, new)
}