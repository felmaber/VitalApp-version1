package juliethosorio.vitalapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaDependientesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaDependientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaDependientesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListaDependientesFragment() {
        // Required empty public constructor
    }


    public static ListaDependientesFragment newInstance(String param1, String param2) {
        ListaDependientesFragment fragment = new ListaDependientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_lista_dependientes, container, false);

        RecyclerView rDependientes = (RecyclerView) vista.findViewById(R.id.RlistaDependientes);
        rDependientes.setHasFixedSize(true);
        RecyclerListaDependientes adapter = new RecyclerListaDependientes(new String[]{"Maria Jose Ramirez", "Juliana Moralez", "Daniel Gutierrez"});
        rDependientes.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rDependientes.setLayoutManager(llm);

        return vista;
    }



    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




}
