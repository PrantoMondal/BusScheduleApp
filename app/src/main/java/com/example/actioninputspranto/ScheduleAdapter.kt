package com.example.actioninputspranto
import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.actioninputspranto.databinding.ScheduleRowBinding

class ScheduleAdapter(val menuItemCallback: (BusSchedule,RowAction) -> Unit) :
    ListAdapter<BusSchedule,ScheduleAdapter.ScheduleViewHolder>(ScheduleDiffUtil()){
    class ScheduleViewHolder(val binding: ScheduleRowBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(busSchedule: BusSchedule){
                    binding.schedule = busSchedule
                }
            }
    class ScheduleDiffUtil : DiffUtil.ItemCallback<BusSchedule>() {
        override fun areItemsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ScheduleRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule : BusSchedule = getItem(position)
        holder.bind(schedule)
        holder.binding.rowFavorite.setOnClickListener {
            schedule.favorite = !schedule.favorite
            holder.bind(schedule)
        }
        val menuIV = holder.binding.menuIV
        holder.binding.menuIV.setOnClickListener {
            val popUpMenu = PopupMenu(menuIV.context, menuIV)
            popUpMenu.menuInflater.inflate(R.menu.row_menu,popUpMenu.menu)
            popUpMenu.show()
            //lambda block
            popUpMenu.setOnMenuItemClickListener {
                val action : RowAction = when(it.itemId){
                    R.id.item_edit -> {
                       RowAction.EDIT
                    }
                    R.id.item_delete -> {
                        RowAction.DELETE
                    }
                    else -> RowAction.NONE
                }
                menuItemCallback(schedule,action)
                true
            }
        }
    }
}
enum class RowAction{
    EDIT, DELETE, NONE
}