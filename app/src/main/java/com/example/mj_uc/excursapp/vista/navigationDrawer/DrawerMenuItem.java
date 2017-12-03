package com.example.mj_uc.excursapp.vista.navigationDrawer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.vista.Help;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_NAV_ADD = 1;//DRAWER_MENU_NAV_ADD
    public static final int DRAWER_MENU_NAV_GROUP = 2;//DRAWER_MENU_NAV_GROUP
    public static final int DRAWER_MENU_NAV_DATE = 3;//DRAWER_MENU_NAV_DATE
    public static final int DRAWER_MENU_NAV_HELP = 4;//DRAWER_MENU_NAV_HELP
    public static final int DRAWER_MENU_NAV_QUERY = 5;
    public static final int DRAWER_MENU_NAV_HELP_TITLE = 6;

    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemNameTxtSub)
    private TextView itemNameTxtSub;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @SuppressLint("NewApi")
    @Resolve
    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_NAV_ADD:
                itemNameTxt.setText("Crear Actividad");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_add_black_24dp));
                break;
            case DRAWER_MENU_NAV_QUERY:
                itemNameTxtSub.setText("Consulta de Actividades");
                itemNameTxtSub.setTextColor(Color.parseColor("#2E64FE"));
                itemIcon.setVisibility(android.view.View.GONE);
                itemNameTxt.setVisibility(android.view.View.GONE);
                break;
            case DRAWER_MENU_NAV_GROUP:
                itemNameTxt.setText("Por Grupo");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_black_24dp));
                break;
            case DRAWER_MENU_NAV_DATE:
                itemNameTxt.setText("Por Fecha");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_date_range_black_24dp));
                break;
            case DRAWER_MENU_NAV_HELP_TITLE:
                itemNameTxtSub.setText("Ayuda");
                itemNameTxtSub.setTextColor(Color.parseColor("#2E64FE"));
                itemIcon.setVisibility(android.view.View.GONE);
                itemNameTxt.setVisibility(android.view.View.GONE);
                break;
            case DRAWER_MENU_NAV_HELP:
                itemNameTxt.setText("Ayuda");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_help_black_24dp));
                break;
        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition) {
            case DRAWER_MENU_NAV_ADD:
                Toast.makeText(mContext, "Go to Create Activity", Toast.LENGTH_SHORT).show();
                if (mCallBack != null) mCallBack.onCreateMenuSelected();
                break;
            case DRAWER_MENU_NAV_GROUP:
                Toast.makeText(mContext, "Go to Query by Group", Toast.LENGTH_SHORT).show();
                if (mCallBack != null) mCallBack.onGroupQueryMenuSelected();
                break;
            case DRAWER_MENU_NAV_DATE:
                Toast.makeText(mContext, "Go to Query by Date", Toast.LENGTH_SHORT).show();
                if (mCallBack != null) mCallBack.onDateQueryMenuSelected();
                break;
            case DRAWER_MENU_NAV_HELP:
                Intent goToHelp = new Intent(mContext,Help.class);
                mContext.startActivity(goToHelp);
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onCreateMenuSelected();
        void onGroupQueryMenuSelected();
        void onDateQueryMenuSelected();
        void onHelpMenuSelected();
    }
}

