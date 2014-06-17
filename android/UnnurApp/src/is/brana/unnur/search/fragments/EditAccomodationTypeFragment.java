package is.brana.unnur.search.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import is.brana.unnur.R;
import is.brana.unnur.objects.AccomodationType;
import is.brana.unnur.search.listeners.EditCategoryChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thibaultguegan on 05/06/2014.
 */
public class EditAccomodationTypeFragment extends Fragment {
    private static final String TAG = EditAreaFragment.class.getName();

    private ListView listView;
    private AccomodationTypeAdapter adapter;
    private EditCategoryChangeListener editCategoryChangeListener;

    private ArrayList<AccomodationType> accomodationTypes = new ArrayList<AccomodationType>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accomodation_type_mode, null);

        listView = (ListView) view.findViewById(android.R.id.list);

        return view;
    }

    public static class ViewHolder
    {
        public TextView txtName;
        public CheckBox groupBox;
    }

    private class AccomodationTypeAdapter extends ArrayAdapter<AccomodationType>
    {
        private ArrayList<AccomodationType> items;
        private LayoutInflater inflater;
        private Context context;
        public ArrayList<AccomodationType> accomodationTypes = new ArrayList<AccomodationType>();

        public AccomodationTypeAdapter(Context context, int textViewResourceId, ArrayList<AccomodationType> items)
        {
            super(context, textViewResourceId, items);
            this.items = items;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = initView();
            }

            bindItem(v, position);

            return v;
        }

        private View initView() {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vi.inflate(R.layout.group_item, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtName = (TextView) v.findViewById(R.id.textTitle);
            viewHolder.groupBox = (CheckBox) v.findViewById(R.id.checkBoxGroup);

            v.setTag(viewHolder);

            return v;
        }

        private void bindItem(View v, final int position)
        {
            final AccomodationType accomodationType = items.get(position);

            ViewHolder viewHolder = (ViewHolder) v.getTag();
            viewHolder.txtName.setText(accomodationType.getName());

            viewHolder.groupBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    if(isChecked)
                    {
                        if(!accomodationTypes.contains(items.get(position)))
                        {
                            accomodationTypes.add(items.get(position));
                        }
                    }
                    else
                    {
                        if(accomodationTypes.contains(items.get(position)))
                        {
                            accomodationTypes.remove(items.get(position));
                        }
                    }

                    editCategoryChangeListener.onEditCategoryChanged(accomodationTypes);
                }
            });
        }
    }

    public void setAccomodationTypes(ArrayList<AccomodationType> accomodationTypes) {
        this.accomodationTypes = accomodationTypes;

        adapter = new AccomodationTypeAdapter(getActivity(), R.layout.group_item, accomodationTypes);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setEditCategoryChangeListener(EditCategoryChangeListener editCategoryChangeListener) {
        this.editCategoryChangeListener = editCategoryChangeListener;
    }

    public List<AccomodationType> getAccomodationTypes()
    {
        return adapter.accomodationTypes;
    }
}
