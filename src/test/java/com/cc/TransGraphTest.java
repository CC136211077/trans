package com.cc;

import constant.NumConstant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransGraphTest {

    private TransGraph transGraph;

    @Before
    public void setUp() throws Exception {
        String str = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
        transGraph = new TransGraph(str);
    }

    /**
     * 第一题
     */
    @Test
    public void calcDistofPathTest01() {
        String testRoute = "A->B->C";
        int dist = transGraph.calcDistofPath(testRoute);
        if (dist == NumConstant.NOT_EXIST) {
            Assert.fail("NO SUCH ROUTE");
        }
        Assert.assertEquals(9,dist);
        System.out.println(dist);
    }

    /**
     * 第二题
     */
    @Test
    public void calcDistofPathTest02() {
        String testRoute = "A->D";
        int dist = transGraph.calcDistofPath(testRoute);
        if (dist == NumConstant.NOT_EXIST) {
            Assert.fail("NO SUCH ROUTE");
        }
        Assert.assertEquals(5,dist);
        System.out.println(5);
    }

    /**
     * 第三题
     */
    @Test
    public void calcDistofPathTest03() {
        String testRoute = "A->D->C";
        int dist = transGraph.calcDistofPath(testRoute);
        if (dist == NumConstant.NOT_EXIST) {
            Assert.fail("NO SUCH ROUTE");
        }
        Assert.assertEquals(13,dist);
        System.out.println(dist);
    }

    /**
     * 第四题
     */
    @Test
    public void calcDistofPathTest04() {
        String testRoute = "A->E->B->C->D";
        int dist = transGraph.calcDistofPath(testRoute);
        if (dist == NumConstant.NOT_EXIST) {
            Assert.fail("NO SUCH ROUTE");
        }
        Assert.assertEquals(22,dist);
        System.out.println(dist);
    }

    /**
     * 第五题
     */
    @Test
    public void calcDistofPathTest05() {
        String testRoute = "A->E->D";
        int dist = transGraph.calcDistofPath(testRoute);
        if (dist == NumConstant.NOT_EXIST) {
            Assert.fail("NO SUCH ROUTE");
        }
        Assert.assertEquals(9,dist);
        System.out.println(dist);
    }

    /**
     * 第六题
     */
    @Test
    public void queryAllRoutesTest01() {
        String testRoute = "C->C";
        int lines = transGraph.queryAllRouteAndSiteAmount(testRoute,3);
        if (lines <= NumConstant.NUM_ZERO) {
            Assert.fail("NO SUCH ROUTE");
        }
        System.out.println("总路线数："+lines);
    }

    /**
     * 第七题
     */
    @Test
    public void queryAllRoutesTest02() {
        String testRoute = "A->C";
        int lines = transGraph.queryAllRouteAndSiteAmount(testRoute,4);
        if (lines <= NumConstant.NUM_ZERO) {
            Assert.fail("NO SUCH ROUTE");
        }
        System.out.println("总路线数："+lines);
    }

    /**
     * 第八题
     */
    @Test
    public void queryMixDistance01() {
        String testRoute = "A->C";
        int lines = transGraph.queryMixDistance(testRoute);
        if (lines <= NumConstant.NUM_ZERO) {
            Assert.fail("NO SUCH ROUTE");
        }
        System.out.println("距离："+lines);
    }

    /**
     * 第九题
     */
    @Test
    public void queryMixDistance02() {
        String testRoute = "B->B";
        int lines = transGraph.queryMixDistance(testRoute);
        if (lines <= NumConstant.NUM_ZERO) {
            Assert.fail("NO SUCH ROUTE");
        }
        System.out.println("距离："+lines);
    }

    /**
     * 第十题
     */
    @Test
    public void queryAllRoutesAndDistance(){
        String testRoute = "C->C";
        int lines = transGraph.queryAllRoutesAndDistance(testRoute,30);
        if (lines <= NumConstant.NUM_ZERO) {
            Assert.fail("NO SUCH ROUTE");
        }
        System.out.println("总路线数："+lines);
    }
}