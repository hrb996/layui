package com.hrb.util;

public class Page {
    private  int dqy;
    private int ydx = 5;
    private int ysy;
    private int zys;
    private int zjr;

    public Page() {
    }

    /**
     * 计算分页的方法
     * @param dqy
     * @param zjr
     */
    public Page(int dqy, int zjr) {
        this.dqy = dqy;
        this.zjr = zjr;
        this.zys = zjr % ydx == 0 ? zjr/ydx : (zjr/ydx+1);
        this.ysy = (dqy-1)*ydx;
    }

    public int getDqy() {
        return dqy;
    }

    public void setDqy(int dqy) {
        this.dqy = dqy;
    }

    public int getYdx() {
        return ydx;
    }

    public void setYdx(int ydx) {
        this.ydx = ydx;
    }

    public int getYsy() {
        return ysy;
    }

    public void setYsy(int ysy) {
        this.ysy = ysy;
    }

    public int getZys() {
        return zys;
    }

    public void setZys(int zys) {
        this.zys = zys;
    }

    public int getZjr() {
        return zjr;
    }

    public void setZjr(int zjr) {
        this.zjr = zjr;
    }
}
