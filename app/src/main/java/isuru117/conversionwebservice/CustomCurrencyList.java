package isuru117.conversionwebservice;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomCurrencyList extends BaseAdapter {


    private Activity context;
    ArrayList<Currency> countries;


    public CustomCurrencyList(Activity context, ArrayList<Currency> countries) {
     //   super(context, R.layout.row_item, countries);
       this.context = context;
       this.countries=countries;

    }

    public static class ViewHolder
    {
        TextView textViewId;
        TextView textViewCurrency;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;

        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;
        if(convertView==null) {
            vh=new ViewHolder();
            row = inflater.inflate(R.layout.row_item, null, true);
            vh.textViewId = (TextView) row.findViewById(R.id.textViewId);
            vh.textViewCurrency = (TextView) row.findViewById(R.id.textViewCurrency);
            // store the holder with the view.
            row.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.textViewCurrency.setText(countries.get(position).getValue());
        vh.textViewId.setText(""+countries.get(position).getId());

        return  row;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {

        if(countries.size()<=0)
            return 1;
        return countries.size();
    }
}

