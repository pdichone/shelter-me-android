package sheltermeapp.magadistudio.com.shelterme.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.net.URI;
import java.util.List;

import sheltermeapp.magadistudio.com.shelterme.DetailsActivity;
import sheltermeapp.magadistudio.com.shelterme.DogsActivity;
import sheltermeapp.magadistudio.com.shelterme.R;
import sheltermeapp.magadistudio.com.shelterme.app.AppController;
import sheltermeapp.magadistudio.com.shelterme.model.Animal;


public class CustomAnimalAdapter extends ArrayAdapter<Animal> {

	private Context mContext;
	private LayoutInflater inflater;
    //private Animal mData[] = null;
    private List<Animal> mData = null;
    private int mLayoutResourceId;
	//private List<Animal> cats;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomAnimalAdapter(Context context, int resource, List<Animal> data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;

    }

    @Override
    public Animal getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(Animal item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

          View row = convertView;
          PlaceHolder holder = null;

        if (row == null) {

            //create a new view
            inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId,parent,false);

            holder = new PlaceHolder();

            //Get a reference to different view elements we wish to update
            holder.thumbnail = (NetworkImageView) row.findViewById(R.id.thumbnail);
            holder.catName = (TextView) row.findViewById(R.id.name);
            holder.catId = (TextView) row.findViewById(R.id.id);
            holder.catSex = (TextView) row.findViewById(R.id.sex);
            holder.primeBreed = (TextView) row.findViewById(R.id.primaryBreed);
            holder.secondBreed = (TextView) row.findViewById(R.id.secondaryBreed);
            holder.ageMonths = (TextView) row.findViewById(R.id.ageMonths);
            holder.webButton = (Button) row.findViewById(R.id.websiteId);
            holder.rowId     = (RelativeLayout) row.findViewById(R.id.rowId);



            row.setTag(holder);  // set tag for each added row

        }else {
            holder = (PlaceHolder) row.getTag(); // Big efficiency here!

        }

        Animal animal = mData.get(position);

        //Setup to reuse the same listener for each row
        holder.thumbnail.setOnClickListener(PopupListener);
        Integer rowPosition = position;
        holder.thumbnail.setTag(rowPosition);

        //Set up website icon onClick listener
        holder.webButton.setOnClickListener(PopWebListener);
        Integer rPosition = position;
        holder.webButton.setTag(rPosition);


        //Set up the view to reflect the data we need to display
        holder.thumbnail.setImageUrl(animal.getPhotoUrl(), imageLoader);
        holder.catName.setText("Name: " + animal.getName());
        holder.catId.setText(animal.getAnimalId());
        holder.catSex.setText("Sex: " + animal.getSex());
        holder.primeBreed.setText("Primary Breed: " + animal.getPrimaryBreed());
        holder.secondBreed.setText("Secondary Breed: " + animal.getSecondaryBreed());
        holder.ageMonths.setText("Age Month: " + animal.getAgeMonths());






//		if (inflater == null)
//			inflater = (LayoutInflater) activity
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		if (convertView == null)
//			convertView = inflater.inflate(R.layout.list_row, null);
//		if (imageLoader == null)
//			imageLoader = AppController.getInstance().getImageLoader();
//		NetworkImageView thumbNail = (NetworkImageView) convertView
//				.findViewById(R.id.thumbnail);
//		TextView catName = (TextView) convertView.findViewById(R.id.name);
//		TextView catId   = (TextView) convertView.findViewById(R.id.id);
//		TextView catSex  = (TextView) convertView.findViewById(R.id.sex);
//		TextView primeBread = (TextView) convertView.findViewById(R.id.primaryBreed);
//        TextView secondBreed = (TextView) convertView.findViewById(R.id.secondaryBreed );
//		TextView ageMonths  = (TextView) convertView.findViewById(R.id.ageMonths);
//
//
//		Animal c = cats.get(position); // get cat positions
//
//		//Get thumbnail image
//		thumbNail.setImageUrl(c.getPhotoUrl(), imageLoader);
//
//		catName.setText(c.getName());
//		catId.setText(c.getAnimalId());
//		catSex.setText("Sex: " + c.getSex());
//		primeBread.setText("Primary Breed: " + c.getPrimaryBreed());
//        secondBreed.setText("Secondary Breed: " + c.getSecondaryBreed());
//		ageMonths.setText("Age (Months): " + c.getAgeMonths());
//
//
//		return convertView;

        return row;
	}

    View.OnClickListener PopWebListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Integer viewPosition = (Integer) v.getTag();
            Animal a = mData.get(viewPosition);
            String mUrl = "http://www.petango.com/Adopt/Dog-Pointer-" + a.getAnimalId().toString() ;
            Uri webpage = Uri.parse(mUrl);
            Intent openWeb = new Intent(Intent.ACTION_VIEW, webpage);
            mContext.startActivity(openWeb);
        }
    };
    View.OnClickListener PopupListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Integer viewPosition = (Integer) v.getTag();
            Animal a = mData.get(viewPosition);
            String mId = a.getAnimalId().toString();
            String name = a.getName().toString();
            String months = String.valueOf(a.getAgeMonths());
            String breed = a.getPrimaryBreed().toString();
            String gender = a.getSex().toString();
            String sp = a.getSpayNeutered();







            Intent i = new Intent(mContext, DetailsActivity.class);
            i.putExtra("animalId", mId);
            i.putExtra("name", name);
            i.putExtra("months", months);
            i.putExtra("breed", breed);
            i.putExtra("gender", gender);
            i.putExtra("spayed", sp);



            mContext.startActivity(i);

           // Toast.makeText(getContext(),a.getName().toString(),Toast.LENGTH_SHORT).show();
        }
    };

    private static class PlaceHolder{
        NetworkImageView thumbnail;
        TextView catName;
        TextView catId;
        TextView catSex;
        TextView primeBreed;
        TextView secondBreed;
        TextView ageMonths;
        Button webButton;
        RelativeLayout rowId;
    }
}
