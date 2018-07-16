package com.example.arielo.momaentregable.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arielo.momaentregable.model.pojo.Artist;
import com.example.arielo.momaentregable.model.pojo.Paint;
import com.example.arielo.momaentregable.view.fragments.DetalleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DH on 27/6/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{
    List<Fragment> fragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<Artist> artistList, List<Paint> paintList) {
        super(fm);
        for (Artist artist : artistList) {
            for (Paint paint : paintList) {
             if (artist.getArtistId().equals(paint.getArtistId()) ){
                 fragmentList.add(DetalleFragment.creadorDeFragmentos(paint, artist));
             }
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
