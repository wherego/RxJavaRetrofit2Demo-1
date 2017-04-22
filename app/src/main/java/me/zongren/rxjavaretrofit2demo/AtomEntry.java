package me.zongren.rxjavaretrofit2demo;

import org.simpleframework.xml.Element;

/**
 * Created by zongren on 2017/4/18.
 * All right reserved by 正奇晟业（北京）科技有限公司
 */
public class AtomEntry {
    @Element
    public String title;
    @Element
    public String summary;
}
