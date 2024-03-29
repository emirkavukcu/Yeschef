package com.example.yeschef;

import android.app.Application;

import com.example.yeschef.jsonObjects.User;
import com.example.yeschef.YesChefRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class YesChefApplication extends Application {
    ExecutorService srv = Executors.newCachedThreadPool();
    String currentUserId;

}
