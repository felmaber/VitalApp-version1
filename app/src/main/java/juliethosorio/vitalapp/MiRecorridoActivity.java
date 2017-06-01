package juliethosorio.vitalapp;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

public class MiRecorridoActivity extends AppCompatActivity implements UnoFragment.OnFragmentInteractionListener,DosFragment.OnFragmentInteractionListener, TresFragment.OnFragmentInteractionListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Button btnTerminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_recorrido);

        btnTerminar=(Button)findViewById(R.id.btnIrRegistro) ;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mi_recorrido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static Fragment newInstance(int sectionNumber) {
            Fragment fragmento=null;
            switch (sectionNumber){
                case 1:
                    fragmento=new UnoFragment();
                    break;
                case 2:
                    fragmento=new DosFragment();
                    break;
                case 3:
                    fragmento=new TresFragment();
                    break;
            }
            Bundle args=new Bundle();
            args.putInt(ARG_SECTION_NUMBER,sectionNumber);
            return fragmento;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_mi_recorrido, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnIrRegistro:
                Intent irLogin = new Intent(this, LoginActivity.class);
                startActivity(irLogin);
                break;
            case R.id.btnQRsplash:
                Snackbar.make(v, "leer QR", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                break;
            case R.id.llamarContactosplash:
                Snackbar.make(v, "llamada a contacto", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                break;
            case R.id.llamadaEmergencia:
                Snackbar.make(v, "llamada de emergencia", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                break;
            case R.id.btnSaltar:
                Intent intentSaltar=new Intent(this, LoginActivity.class);
                startActivity(intentSaltar);
                break;

        }
    }

}
