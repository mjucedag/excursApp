package com.example.mj_uc.excursapp.vista.navigationDrawer;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.mj_uc.excursapp.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Layout(R.layout.drawer_header)
public class DrawerHeader {

    @View(R.id.profileImageView)
    private ImageView profileImage;

    @Resolve
    private void onResolved() {

    }
}
