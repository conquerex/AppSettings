package com.jongkook.android.threadbasic_tetris;

import java.util.Random;

/**
 * Created by jongkook on 2016. 10. 17..
 */
public class Block {
    private int[][][] currentBlockGroup;
    private int[][] currentBlock;
    private int currentOrientation;
    private int currentOrientationLimit = 0;

    private static final int ABS_X = 5;
    private static final int ABS_Y = 0;
    int x = 0, y = 0;

    public void setBlock(){ // type = 0 to 6
        Random random = new Random();
        int type = random.nextInt();
        // 블럭의 회전상태를 포함한 블럭 그룹을 선택
        currentBlockGroup = blocks[type];
        // 블럭의 회전방향을 선택
        currentOrientation = 0;
        // 블럭의 회전방향이 블럭의 타입에 따라 다르므로 회전 방향 개수를 제한해준다
        currentOrientationLimit = currentBlockGroup.length;
        // 현재 회전방향의 블럭을 세팅한다
        currentBlock = currentBlockGroup[currentOrientation];

        // 최초 위치
        x = ABS_X;
        y = ABS_Y;
    }

    public void rotateBlock(){
        // 회전방향을 무조건 +1을 하고
        currentOrientation++;
        // 현재 블럭이 가지고 있는 회전 방향 제한 수로 나눠서 나머지값을 회전 방향으로 한다
        currentOrientation = currentOrientation / currentOrientationLimit;
        currentBlock = currentBlockGroup[currentOrientation];
    }

    public int[][] getCurrentBlock(){
        return currentBlock;
    }

    int blocks[][][][] = {
            {
                    // Block I
                    {
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0}
                    },
                    {
                            {1, 1, 1, 1},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block J
            {
                    {
                            {0, 0, 2, 0},
                            {0, 0, 2, 0},
                            {0, 2, 2, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 2, 0, 0},
                            {0, 2, 2, 2},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 2, 2, 0},
                            {0, 2, 0, 0},
                            {0, 2, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {2, 2, 2, 0},
                            {0, 0, 2, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block L
            {
                    {
                            {0, 3, 0, 0},
                            {0, 3, 0, 0},
                            {0, 3, 3, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 3, 3, 3},
                            {0, 3, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 3, 3, 0},
                            {0, 0, 3, 0},
                            {0, 0, 3, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 3, 0},
                            {3, 3, 3, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block S
            {
                    {
                            {0, 0, 0, 0},
                            {0, 4, 4, 0},
                            {4, 4, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 4, 0, 0},
                            {0, 4, 4, 0},
                            {0, 0, 4, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block Z
            {
                    {
                            {0, 0, 0, 0},
                            {0, 5, 5, 0},
                            {0, 0, 5, 5},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 5, 0},
                            {0, 5, 5, 0},
                            {0, 5, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block T
            {
                    {
                            {0, 6, 0, 0},
                            {0, 6, 6, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {6, 6, 6, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 6, 0, 0},
                            {6, 6, 0, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 6, 0, 0},
                            {6, 6, 6, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block O
            {
                    {
                            {0, 0, 0, 0},
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0}
                    }
            },
    };
}
