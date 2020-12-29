package com.cc;

import constant.NumConstant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: CC
 * @date: 2020/12/24 22:41
 * @Description: 图数据结构
 */

public class TransGraph {

    /**
     * 顶点间关系
     */
    private Map<String, Map<String, Integer>> vertexRelation;

    /**
     * 顶点数
     */
    private int num;

    /**
     * 顶点集合
     */
    private HashSet<String> vertexs;

    public TransGraph(String str) {
        vertexRelation = new HashMap<>();
        vertexs = new HashSet<>();
        if (str == null) {
            return;
        }
        String[] lines = str.split(",");
        Stream.of(lines).filter(e -> e.length() == 3).forEach(e -> {
            String start = String.valueOf(e.charAt(0));
            String end = String.valueOf(e.charAt(1));
            int width = e.charAt(2) - '0';
            Map<String, Integer> stringIntegerMap = vertexRelation.get(start);
            if (stringIntegerMap == null) {
                stringIntegerMap = new HashMap<>();
                vertexRelation.put(start, stringIntegerMap);
            }
            stringIntegerMap.put(end, width);
            if (vertexs.add(start)) {
                num++;
            }
            if (vertexs.add(end)) {
                num++;
            }
        });
    }

    /**
     * 查看路径距离
     *
     * @param path
     * @return
     */
    public int calcDistofPath(String path) {
        int dist = NumConstant.NUM_ZERO;
        if (path == null || path.length() <= NumConstant.NUM_ZERO) {
            return NumConstant.NOT_EXIST;
        }
        String[] lines = path.split("->");
        String start = lines[NumConstant.NUM_ZERO];
        for (int i = 1; i < lines.length; i++) {
            String end = lines[i];
            Map<String, Integer> stringIntegerMap = vertexRelation.get(start);
            if (stringIntegerMap != null && stringIntegerMap.containsKey(end)) {
                dist += stringIntegerMap.get(end);
            } else {
                return NumConstant.NOT_EXIST;
            }
            start = end;
        }
        return dist;
    }

    /**
     * 查询所有满足条件的路线
     *
     * @param path
     * @param site
     * @return
     */
    public int queryAllRouteAndSiteAmount(String path, int site) {
        List<Object[]> allRoutes = this.queryAllRoutes(path);
        if (allRoutes != null && allRoutes.size() > NumConstant.NUM_ZERO) {
            allRoutes = allRoutes.stream().filter(e -> e.length < site + NumConstant.NUM_TWO).collect(Collectors.toList());
            if (allRoutes != null && allRoutes.size() > NumConstant.NUM_ZERO) {
                allRoutes.stream().forEach(e -> {
                    System.out.print("路线：");
                    Stream.of(e).forEach(s -> {
                        System.out.print(s + " ");
                    });
                    System.out.println();
                });
            }
            return allRoutes.size();
        }
        return NumConstant.NOT_EXIST;
    }

    /**
     * 查询两地之间最短距离
     * @param path
     * @return
     */
    public int queryMixDistance(String path) {
        Map<Integer, String> map = new HashMap();
        List<Object[]> allRoutes = this.queryAllRoutes(path);
        if (allRoutes != null && allRoutes.size() > NumConstant.NUM_ZERO) {
            allRoutes.stream().forEach(e -> {
                String str = "";
                for (Object temp : e) {
                    str += temp + "->";
                }
                int distance = this.calcDistofPath(str);
                map.put(distance, str.substring(NumConstant.NUM_ZERO, str.length() - NumConstant.NUM_TWO));
            });
            Integer distance = Arrays.stream(map.keySet().toArray(new Integer[map.keySet().size()])).min(Comparator.comparing(Integer::intValue)).get();
            System.out.println("最短路线：" + map.get(distance));
            return distance;
        }
        return NumConstant.NOT_EXIST;
    }

    public int queryAllRoutesAndDistance(String path,Integer journey) {
        Map<Integer, String> map = new HashMap();
        List<Object[]> allRoutes = this.queryAllRoutes(path);
        if (allRoutes != null && allRoutes.size() > NumConstant.NUM_ZERO) {
            allRoutes.stream().forEach(e -> {
                String str = "";
                for (Object temp : e) {
                    str += temp + "->";
                }
                int distance = this.calcDistofPath(str);
                if (distance < journey) {
                    map.put(distance, str.substring(NumConstant.NUM_ZERO, str.length() - NumConstant.NUM_TWO));
                }
            });
            for(Map.Entry<Integer,String> temp: map.entrySet()){
                System.out.println("路线："+temp.getValue()+" 距离："+temp.getKey());
            }
            return map.size();
        }
        return NumConstant.NOT_EXIST;
    }




    /**
     * 查看所有的路径
     *
     * @param path
     * @return
     */
    public List<Object[]> queryAllRoutes(String path) {
        String[] line = path.split("->");
        String start = line[NumConstant.NUM_ZERO];
        String end = line[1];
        List<Object[]> allRoutes = new ArrayList<>();
        Stack<String> route = new Stack<>();
        if (!vertexs.contains(start) || !vertexs.contains(end)) {
            return null;
        }
        this.depthTraversal(allRoutes, start, end, route);
        return allRoutes;
    }

    /**
     * 深度遍历
     *
     * @param allRoutes 所有路线
     * @param start     起始位置
     * @param end       终点位置
     * @param route     路线
     */
    public void depthTraversal(List<Object[]> allRoutes, String start, String end, Stack<String> route) {
        if (vertexRelation.containsKey(start)) {
            Map<String, Integer> stringIntegerMap = vertexRelation.get(start);
            route.push(start);
            for (Map.Entry<String, Integer> entry : stringIntegerMap.entrySet()) {
                if (entry.getKey().equals(end)) {
                    route.push(end);
                    allRoutes.add(route.toArray());
                    route.pop();
                } else if (!route.contains(entry.getKey())) {
                    start = entry.getKey();
                    this.depthTraversal(allRoutes, start, end, route);
                }
            }
            route.pop();
        }
    }


}
