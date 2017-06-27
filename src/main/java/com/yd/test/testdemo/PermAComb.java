package com.yd.test.testdemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/*
 * 打印数组排列组合
 * 1 数组不能重复
 * 2 数组第二位不能是4
 * 3 数组3 5 不能相邻
 */
public class PermAComb {
    static List<int[]> allSorts = new ArrayList<int[]>();
    //公共基础方法    
    public static void permutation(int[] nums, int start, int end) {
    	
        if (start == end) { // 当只要求对数组中一个数字进行全排列时，只要就按该数组输出即可
            int[] newNums = new int[nums.length]; // 为新的排列创建一个数组容器
            for (int i=0; i<=end; i++) {
                newNums[i] = nums[i];
            }
            allSorts.add(newNums); // 将新的排列组合存放起来
        } else {
            for (int i=start; i<=end; i++) {
                int temp = nums[start]; // 交换数组第一个元素与后续的元素
                nums[start] = nums[i];
                nums[i] = temp;
                permutation(nums, start + 1, end); // 后续元素递归全排列
                nums[i] = nums[start]; // 将交换后的数组还原
                nums[start] = temp;
            }
        }
    }
      
    public static void Aprint(int[] numArray){
    	permutation(numArray, 0, numArray.length - 1);
        int[][] a = new int[allSorts.size()][]; // 你要的二维数组a
        allSorts.toArray(a);
  
        // 打印验证
        for (int i=0; i<a.length; i++) {
            int[] nums = a[i];
            for (int j=0; j<nums.length; j++) {
                System.out.print(nums[j]);
            }
            System.out.println();
        }
        System.out.println(a.length);

    }
    
    //获取所有排列组合
    public static List<int[]> allList(int[] numArray){
    	permutation(numArray, 0, numArray.length - 1);
    	List<int[]> allSortslist = new ArrayList<int[]>();
    	allSortslist=allSorts;  
    //	System.out.println("alllist size:"+allSortslist.size());
    //	printList(allSortslist);
		return allSortslist;

    }
    
   //打印list 
    public static void printList(List<int[]> list){
    	System.out.println("listsize:==="+list.size());
    	int[][] a = new int[list.size()][]; // 你要的二维数组a
    	list.toArray(a);
  
        // 打印验证
        for (int i=0; i<a.length; i++) {
            int[] nums = a[i];
            for (int j=0; j<nums.length; j++) {
                System.out.print(nums[j]);
            }
            System.out.println();
        }
      
    }
    
    public static List<int[]> removeDuplicate(List<int[]> list){
    	List<int[]> templist=new ArrayList<int[]>();
    	boolean flag=false;
    	
    	for(int i=0;i<list.size();i++){
    		for(int j=i+1;j<list.size();j++){
    			if(compareArray(list.get(i),list.get(j))){
    				flag=true;
    				list.remove(list.get(j));
    				break;
    			}else{
    				flag=false;
    				//break;
    			}  			
    		}
    	}

		return list;
    	
    }
    //比较数组	
    public static boolean compareArray(int[]m,int[]n){
    	boolean flag=false;
    	for(int i=0;i<m.length;i++){
    		if(m[i]==n[i]){
    			flag=true;
    		}else{
    			flag=false;
    			break;
    		}
    	}
		return flag;
    }
    
    //检查数组第二位是不是4
    public static boolean checkArray4(int[] a){
    	if(a[1]==4){
    		return true;
    	}
		return false;
    }
    
    //检查数组3 5是不是相邻
    public static boolean checkArray35(int[]a){
    	for(int i=0;i<a.length-1;i++){
    		
    		if((a[i]==3&a[i+1]==5)||(a[i]==5&a[i+1]==3)){
    			return true;   			
    		}
    	}
    	return false;
    }
    
    
    public static List<int[]> remove4(List<int[]> list){
    	List<int[]> templist=new ArrayList<int[]>();
    	for(int i=0;i<list.size();i++){
    		if(!(checkArray4(list.get(i)))){
    			templist.add(list.get(i));
    		}
    	}
		return templist;
    }
    
    public static List<int[]> remove35(List<int[]> list){
    	List<int[]> templist=new ArrayList<int[]>();
    	for(int i=0;i<list.size();i++){
    		if(!(checkArray35(list.get(i)))){
    			templist.add(list.get(i));
    		}
    	}
		return templist;
    }
    
    
    public static void getPermutation(int[]numArray){
    	
    	 printList(remove4((remove35(removeDuplicate(allList(numArray))))));
    }
    
    /*
     * 打印数组排列组合
     * 1 数组不能重复
     * 2 数组第二位不能是4
     * 3 数组3 5 不能相邻
     */
    public static void main(String[] args) {
    	
       int[] numArray = {1,2,2,3,4,5,6,7};

      getPermutation(numArray);
    }
}
