package com.base.java.util.algrothim.ga;

import com.google.common.collect.Lists;
import org.apache.commons.lang.math.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class GADemo {

    /**
     * 遗传算法:
     *  1.编码: 遗传算法在解空间之前,需要将解数据表示成遗传空间的基因型数据,这些串结构数据的不同组合构成了不同的染色体
     *  2.初始化:即生成初始种群,具体的做法是随机生成N个初始的染色体,每一个染色体其实就相当于一个个体,N个个体构成了一个初始化种群,遗传算法
     *      以这N个个体作为初始值开始进化
     *  3.适应度评估,适应度表明个体或者解的优劣性,不同的问题,定义适应度函数的方式也不同,比如如果是求一个函数的最大值,那么一般某个解
     *  对应的函数值越大,那么这个个体的适应度也就越高
     *  4.选择:选择的目的是为了从当前种群中选出优良的个体,使他们有机会成为父代繁殖下一代子孙,遗传算法通过选择过程体现这一思想.
     *      进行选择的原则是适应强的个体为下一代贡献一个或者多个后代的概率大,这体现了达尔文的适者生存的原则.
     *  5.交叉:交叉操作是遗传算法中最主要的遗传操作,通过交叉可以得到新一代的个体,新个体组合了其父代的个体特征.
     *      交叉体现了信息交换的思想
     *  6.变异:变异首先在群体中随机选择一个个体,对于选中的个体以一定的概率,通常是比较小的概率,这与自然界一直,随机改变染色体中某个基因的值
     *
     */


    private static final int max_gen = 100;
    private static final double mutation_percentage = 0.01;
    private static final double cross_percentage = 0.75;
    private static final int size_pop = 4;

    private static double [][] chrom;
    /**每个种群的适应度*/
    private static double []fitNess;
    /**每一代的最优值*/
    private static double bestfitness[];
    /**变量下界*/
    private static int bound_down = 0;
    /**变量上界*/
    private static int bound_up = Integer.MAX_VALUE;
    /**染色体长度*/
    private static int len_chrom = 5;

    /**每个个体被选中的概率*/
    private static double fitness_prob[] = new double[size_pop];

    /**所有计划中的最优值*/
    double gbest;
    /**取得最优质的迭代次数的代号*/
    int gbest_index;

    private static ArrayList resultList = Lists.newArrayList();



    public static void main(String[] args) {

    }


    public static double sum(List<Integer> inputList) {
        double sum =  0;
        for (Integer input : inputList) {
            sum += input;
        }
        return sum;
    }


    static double[]  min() {
        double min_fit = fitNess[0];
        double min_index = 0;

        double result[] = new double[2];
        for(int i = 1; i < size_pop; i++) {
            if(fitNess[i] < min_fit) {
                min_fit = fitNess[i];
                min_index = i;
            }
        }
        result[0] = min_index;
        result[1] = min_fit;
        return result;
    }

    void init_chrom() {
        for(int i = 0; i < size_pop; i++) {
            for(int j = 0; j< len_chrom; j ++) {
                chrom[i][j] = bound_up * ((RandomUtils.nextInt())/bound_up);
            }
            fitNess[i] = fit_func(1.0, 1.0, 1.0, 1.0);
        }
    }

    /**
     *
     */
    static double fit_func(double a1, double a2, double a3, double a4) {
        double A = (a1 * a2)/a3 - (a1 * a4)/(a2 * a3);
        double B  = (a3 / a4) - a4/(a1* a2);
        return  A + B;
    }

    static void select(double chrom[][]) {
        int index[] = new int[size_pop];
        for(int i = 0; i < size_pop; i++) {
            double[] arr = chrom[i];
            //fitNess[i] = 1/fit_func(chrom[0], chrom[1], chrom[2], chrom[3])
        }

        double sum_fitness = 0;
        for(int i = 0; i < size_pop; i++) {
            sum_fitness += fitNess[i];
        }

        for(int i = 0; i < size_pop; i ++) {
            fitness_prob[i] = fitNess[i] / sum_fitness;
        }

        for(int i = 0; i < size_pop; i ++) {
            fitNess[i] = 1/fitNess[i];
        }

        for(int i = 0; i < size_pop; i++) {
            double pick = RandomUtils.nextInt() / bound_up;
            while (pick < 0.001) {
                pick = RandomUtils.nextInt() / bound_up;
            }
            for(int j = 0; j < size_pop; j++) {
                pick = pick - fitness_prob[j];
                if(pick <= 0) {
                    index[i] = j;
                    break;
                }
            }
        }

        for(int i = 0; i < size_pop; i ++) {
            for(int j = 0; j < len_chrom; j++) {
                chrom[i][j] = chrom[index[i]][j];
            }
            fitNess[i] = fitNess[index[i]];
        }
    }

    /**
     * 交叉操作
     * @param chrom
     */
    static void mutation(double chrom[][]) {
        for(int i = 0; i < size_pop; i++) {
            double pick = RandomUtils.nextInt() / bound_up;
            int choice = (int) (pick * size_pop);
            while (choice > size_pop  - 1) {
                 pick = RandomUtils.nextInt() / bound_up;
                 choice = (int) (pick * size_pop);
            }
            pick = RandomUtils.nextInt() / bound_up;
            if(pick > mutation_percentage)
                continue;
            pick = RandomUtils.nextInt() / bound_up;
            int pos = (int)(pick*len_chrom);
            while (pos > len_chrom - 1) {
                pick = RandomUtils.nextInt() / bound_up;
                choice = (int) (pick * size_pop);
            }

            double v =  chrom[i][pos];
            double v1 = v - bound_up;
            double v2 = bound_down - v;
            double r = RandomUtils.nextInt() / bound_up;
            double r1 = RandomUtils.nextInt() / bound_up;

            if(r >= 0.5)
                chrom[i][pos] =  v - v1*r1*(1-((double)i)/max_gen)*(1-((double)i)/max_gen);
        }
    }
}
