package com.example.bottmnavi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class Frag1 extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    int flag=0;
    int year, month, day;
    CalendarDay selectedday;
    Bundle bundle;
    MaterialCalendarView materialCalendarView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1,container,false);
        materialCalendarView = (MaterialCalendarView)view.findViewById(R.id.view);

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new oneDayDecorator());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull final MaterialCalendarView widget, @NonNull final CalendarDay date, boolean selected) {
                /*bundle.putIntArray("date", date.);*/
                selectedday=date;
                ondateselected(widget, date, selected);
            }
        });
        return view;
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {

    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {

    }

    public void ondateselected(@NonNull final MaterialCalendarView widget, @NonNull final CalendarDay date, boolean selected){
        AlertDialog.Builder plans = new AlertDialog.Builder(getActivity());
        int mon = date.getMonth()+1;
        plans.setTitle(mon+"월 "+date.getDay()+"일");


        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.plans, null);
        plans.setView(view);
        plans.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                widget.addDecorator(new EventDecorator(Color.CYAN, date));

                dialogInterface.dismiss();
            }
        });

        plans.setNegativeButton("추가", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(getActivity(), add_schedule_dialog.class);
                int year = date.getYear();
                int month = date.getMonth() +1;
                int day = date.getDay();
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                startActivity(intent);
                flag=1;
            }

        });

        plans.show();

    }
}