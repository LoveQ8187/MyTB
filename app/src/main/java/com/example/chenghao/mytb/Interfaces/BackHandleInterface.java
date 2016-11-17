package com.example.chenghao.mytb.Interfaces;

import com.example.chenghao.mytb.Fragments.BackHandleFragment;

/**
 * Created by chenghao on 2016/11/17.
 * 如果每个Fragment有对返回事件的特殊消费，利用此接口实现回调，让fragment告知activity响应哪一个返回事假
 */
public interface BackHandleInterface {
     void setSelectedFragment(BackHandleFragment backHandleFragment);
}
