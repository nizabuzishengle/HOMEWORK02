package cn.edu.sdwu.android02.home.sn170507180211;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void work1(View view){
        Intent intent=new Intent(this,Activity1.class);
        startActivity(intent);
    }
    public  void work2(View view){
        Intent intent=new Intent(this,Activity2.class);
        startActivity(intent);
    }
    public void work3(View view){
        Intent intent=new Intent(this,Activity3.class);
        startActivity(intent);
    }

}
