package sheltermeapp.magadistudio.com.shelterme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button catsButton;
    private Button dogsButton;
    private Button rabbitsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        catsButton = (Button) findViewById(R.id.catButton);
        catsButton.setOnClickListener(this);

        dogsButton = (Button) findViewById(R.id.dogButton);
        dogsButton.setOnClickListener(this);

        rabbitsButton = (Button) findViewById(R.id.rottentsButton);
        rabbitsButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.catButton:
                startActivity(new Intent(this, CatsActivity.class));
                break;
            case R.id.dogButton:
                startActivity(new Intent(this, DogsActivity.class));
                break;
            case R.id.rottentsButton:
                startActivity(new Intent(this, RottentsActivity.class));
                break;
        }
    }
}
