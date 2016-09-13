package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bigwood928 on 8/13/2016.
 */
public class M5sterMind {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numTestCases = input.nextInt();
        for(int i=0; i<numTestCases;i++) {
            int numLevels = input.nextInt();
            int numEnemies = input.nextInt();
            ArrayList<Level> levels = new ArrayList<Level>();

            for(int level=0; level<numLevels; level++) {
                levels.add(new Level());
                for(int enemy=0; enemy<numEnemies; enemy++) {
                    levels.get(level).addEnemy(new Enemy(input.nextInt()));
                }
            }
            for(int level=0; level<numLevels; level++) {
                for(int enemy=0; enemy<numEnemies; enemy++) {
                    levels.get(level).enemies.get(enemy).bullets = input.nextInt();
                }
            }
            int startingBullets = 0;
            int bulletsFromPreviousLevel = 0;

            for(int j=0;j<levels.size();j++) {
                Level level = levels.get(j);
                Level nextLevel = null;
                if(j+1<levels.size()) {
                    nextLevel = levels.get(j+1);
                }
                Enemy enemy = level.findBestEnemyToKill(nextLevel, bulletsFromPreviousLevel);
                if(enemy.power - bulletsFromPreviousLevel > 0) {
                    startingBullets=startingBullets + (enemy.power-bulletsFromPreviousLevel);
                }
                bulletsFromPreviousLevel = enemy.bullets;
            }
            System.out.println(startingBullets);

        }
    }


    public static class Level {
        private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        private ArrayList<Edge> edges = new ArrayList<Edge>();

        public void addEnemy(Enemy enemy) {
            enemies.add(enemy);
        }

        public void updateEdges(Level nextLevel) {
           // for()
        }

        public Enemy findBestEnemyToKill(Level nextLevel, int bulletsFromPreviousLevel) {
            Enemy bestEnemy = new Enemy(Integer.MAX_VALUE);
            boolean foundBest = false;
            Enemy bestValue = null;
            if(nextLevel != null) {
                Enemy weakestNextEnemy = nextLevel.findWeakestEnemy();

                for (Enemy enemy : enemies) {
                    //best to kill is now best enemy
                    if (enemy.canKillEnemy(weakestNextEnemy) && enemy.power < bulletsFromPreviousLevel) {
                        return enemy;
                    }
                    if (enemy.canKillEnemy(weakestNextEnemy) && enemy.power < bestEnemy.power) {
                        bestEnemy = enemy;
                        foundBest = true;
                    }
                    if(bestValue == null) {
                        bestValue = enemy;
                    } else if(enemy.value() > bestValue.value()) {
                        bestValue = enemy;
                    }
                }
                if(!foundBest) {
                    bestEnemy = bestValue;
                }
                return bestEnemy;
            } else {
                return this.findWeakestEnemy();
            }
        }

        private Enemy findWeakestEnemy() {
            Enemy weakest = new Enemy(Integer.MAX_VALUE);
            for(Enemy enemy: enemies) {
                if(enemy.power < weakest.power) {
                    weakest = enemy;
                }
            }
            return weakest;
        }

        public Enemy findStrongestEnemy() {
            Enemy strongest = new Enemy(0);
            for(Enemy enemy : enemies) {
                if(enemy.power > strongest.power) {
                    strongest = enemy;
                }
            }
            return strongest;
        }
    }

    public static class Edge {
        private final Enemy source;
        private final Enemy destination;
        private final int weight;

        public Edge(Enemy source, Enemy destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static class Enemy {
        public int power;
        public int bullets;

        public Enemy(int power) {
            this.power = power;
        }

        public int value() {
            return bullets-power;
        }

        public boolean canKillEnemy(Enemy enemy) {
            return this.bullets >= enemy.power;
        }
    }
}
