package ru.supplyphotos.presentation.fragments.manuals;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.facebook.drawee.view.SimpleDraweeView;
import org.parceler.Parcels;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.manuals.Guide;
import static ru.supplyphotos.constants.Constants.GUIDE;

/**
 * @author user on 12.01.2018.
 */
public class PageManual extends MvpAppCompatFragment {
    //BindViews
    @BindView(R.id.header_manual_text)
    TextView headerManualText;
    @BindView(R.id.manual_text)
    TextView manualText;
    @BindView(R.id.manual_view)
    SimpleDraweeView manualView;

    private Guide guide;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guide = Parcels.unwrap(getArguments().getParcelable(GUIDE));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.fragment_page_manual, container, false);
        ButterKnife.bind(this,view);
        //Run
        startShow();
        return  view;
    }

    private void startShow(){
        headerManualText.setText(guide.getHeader());
        manualText.setText(guide.getText());
        manualView.setImageURI(Uri.parse(guide.getImage480()));
    }
}
