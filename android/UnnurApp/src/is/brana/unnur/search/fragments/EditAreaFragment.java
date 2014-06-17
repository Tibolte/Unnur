package is.brana.unnur.search.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import is.brana.unnur.R;
import is.brana.unnur.objects.Area;
import is.brana.unnur.search.listeners.EditAreaChangeListener;
import is.brana.unnur.utils.animatedExpandableListview.AnimatedExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thibaultguegan on 03/06/2014.
 */
public class EditAreaFragment extends Fragment {
    private static final String TAG = EditAreaFragment.class.getName();

    private AnimatedExpandableListView listView;
    private ExampleAdapter adapter;
    private EditAreaChangeListener editAreaChangeListener;

    private ArrayList<Area> areas = new ArrayList<Area>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_mode, null);

        List<GroupItem> items = new ArrayList<GroupItem>();

        // Populate our list with groups and it's children
        for(int i = 1; i < 100; i++) {
            GroupItem item = new GroupItem();

            item.title = "Group " + i;

            for(int j = 0; j < i; j++) {
                ChildItem child = new ChildItem();
                child.title = "Awesome item " + j;
                child.hint = "Too awesome";

                item.items.add(child);
            }

            items.add(item);
        }

        adapter = new ExampleAdapter(getActivity());

        adapter.setData(items);

        listView = (AnimatedExpandableListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroupWithAnimation(groupPosition);
                } else {
                    listView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });

        return view;
    }


    @Override
    public void onViewCreated(android.view.View view, android.os.Bundle savedInstanceState)
    {

    }

    private static class GroupItem {
        String title;
        boolean checked;
        List<ChildItem> items = new ArrayList<ChildItem>();
    }

    private static class ChildItem {
        String title;
        boolean checked;
        String hint;
    }

    private static class ChildHolder {
        TextView title;
        TextView hint;
        CheckBox childBox;
    }

    private static class GroupHolder {
        TextView title;
        CheckBox groupBox;
    }

    /**
     * Adapter for our list of {@link GroupItem}s.
     */
    private class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
        private LayoutInflater inflater;

        private List<GroupItem> items;

        private Map<Integer, List<Integer>> mCheckedStates;
       // private EditAreaChangeListener editAreaChangeListener;

        public ExampleAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            mCheckedStates = new HashMap<Integer, List<Integer>>();
            //editAreaChangeListener = editAreaChgListener;
        }

        public void setData(List<GroupItem> items) {
            this.items = items;
        }

        @Override
        public ChildItem getChild(int groupPosition, int childPosition) {
            return items.get(groupPosition).items.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getRealChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder holder;
            final ChildItem item = getChild(groupPosition, childPosition);
            if (convertView == null) {
                holder = new ChildHolder();
                convertView = inflater.inflate(R.layout.list_item, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.textTitle);
                holder.hint = (TextView) convertView.findViewById(R.id.textHint);
                holder.childBox = (CheckBox) convertView.findViewById(R.id.checkBoxChild);

                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }

            holder.title.setText(item.title);
            holder.hint.setText(item.hint);
            holder.childBox.setOnCheckedChangeListener(null);
            holder.childBox.setChecked(item.checked);

            holder.childBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    Integer realPosition = groupPosition;

                    item.checked = isChecked;

                    if(isChecked)
                    {
                        if (mCheckedStates.containsKey(realPosition)) {
                            //get list and add this position
                            List<Integer> list = mCheckedStates.get(realPosition);

                            //double check if this position is already here:
                            if(!list.contains(childPosition))
                            {
                                list.add(childPosition);
                                Log.d(TAG, mCheckedStates.toString());
                                editAreaChangeListener.onEditAreaChanged(mCheckedStates);
                            }
                            else
                            {
                                //irrelevant
                                Log.d(TAG, "Child position already here");
                            }
                        }
                        else
                        {   //nothing set yet, create key + associated list
                            List<Integer> list = new ArrayList<Integer>();
                            list.add(childPosition);

                            mCheckedStates.put(realPosition, list);
                            Log.d(TAG, mCheckedStates.toString());
                            editAreaChangeListener.onEditAreaChanged(mCheckedStates);
                        }
                    }
                    else
                    {
                        //try to remove
                        if (mCheckedStates.containsKey(realPosition)) {
                            //double check if this position is already here:

                            List<Integer> list = mCheckedStates.get(realPosition);

                            if(list.contains(childPosition))
                            {
                                int index = list.indexOf(childPosition);
                                list.remove(index);

                                //uncheck parent
                                GroupItem groupItem = getGroup(groupPosition);
                                groupItem.checked = false;

                                Log.d(TAG, mCheckedStates.toString());
                                editAreaChangeListener.onEditAreaChanged(mCheckedStates);
                            }
                            else
                            {
                                Log.d(TAG, "Child position not here, don't remove");
                            }
                        }
                        else
                        {
                            Log.d(TAG, "Child position unchecked, key realPosition not here, this doesn't make sense to go in this condition");
                        }
                    }

                    notifyDataSetChanged();

                }
            });

            return convertView;
        }

        @Override
        public int getRealChildrenCount(int groupPosition) {
            return items.get(groupPosition).items.size();
        }

        @Override
        public GroupItem getGroup(int groupPosition) {
            return items.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return items.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            final GroupHolder holder;
            final GroupItem item = getGroup(groupPosition);
            if (convertView == null) {
                holder = new GroupHolder();
                convertView = inflater.inflate(R.layout.group_item, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.textTitle);
                holder.groupBox = (CheckBox) convertView.findViewById(R.id.checkBoxGroup);

                convertView.setTag(holder);

            } else {
                holder = (GroupHolder) convertView.getTag();

            }

            holder.title.setText(item.title);
            holder.groupBox.setOnCheckedChangeListener(null);
            holder.groupBox.setChecked(item.checked);

            holder.groupBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    Integer realPosition = groupPosition;

                    item.checked = isChecked;

                    if(isChecked)
                    {
                        List<Integer> list = new ArrayList<Integer>();

                        for(int i=0; i<item.items.size(); i++)
                        {
                            list.add(i);

                            ChildItem childItem = getChild(groupPosition, i);
                            childItem.checked = isChecked;
                        }

                        mCheckedStates.put(realPosition, list);

                        Log.d(TAG, mCheckedStates.toString());
                        editAreaChangeListener.onEditAreaChanged(mCheckedStates);

                    }
                    else
                    {
                        if (mCheckedStates.containsKey(realPosition)) {
                            mCheckedStates.remove(realPosition);
                            editAreaChangeListener.onEditAreaChanged(mCheckedStates);
                        }

                        Log.d(TAG, mCheckedStates.toString());
                    }

                    for(int i=0; i<item.items.size(); i++)
                    {
                        item.items.get(i).checked = isChecked;
                    }

                    notifyDataSetChanged();
                }
            });

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }

        public Map<Integer, List<Integer>> getmCheckedStates() {
            return mCheckedStates;
        }
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;

        List<GroupItem> items = new ArrayList<GroupItem>();

        for(int i=0; i<areas.size(); i++)
        {
            GroupItem groupItem = new GroupItem();

            groupItem.title = areas.get(i).getName();

            for(int j=0; j<areas.get(i).getZipCodes().size(); j++)
            {
                ChildItem childItem = new ChildItem();

                childItem.title = String.valueOf(areas.get(i).getZipCodes().get(j).getZipCode());
                childItem.hint = areas.get(i).getZipCodes().get(j).getTitle();

                groupItem.items.add(childItem);
            }

            items.add(groupItem);
        }

        adapter.setData(items);
    }

    public void setEditAreaChangeListener(EditAreaChangeListener editAreaChangeListener) {
        this.editAreaChangeListener = editAreaChangeListener;
    }

    public Map<Integer, List<Integer>> getCheckedStates()
    {
        return adapter.mCheckedStates;
    }
}
