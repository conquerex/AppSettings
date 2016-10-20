package com.jongkook.android.threadbasic_tetris;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by fastcampus on 2016-10-17.
 */
public class Block extends Thread{
    int x = 5;
    int y = 0;
    int width = 4;
    int height = 4;
    // 2016.10.19 점수 가중치
    int pointWeight = 1;

    int orientation_limit = 0;
    int orientation = 0;
    private int blocktype[][][];
    private int block[][];

    boolean alive = true;
    int interval = 0;
    Handler handler;
    Message msg;

    public void draw(){
        handler.sendEmptyMessage(Stage.REFRESH);
    }

    public int[][] getBlock(){
        return block;
    }

    public Block(int[][][] blocktype, int interval, Handler handler){
        this.blocktype = blocktype;
        this.interval = interval;
        orientation_limit = this.blocktype.length;
        block = this.blocktype[orientation];
        this.handler = handler;
    }

    // 회전
    public void rotate(){
        orientation++;
        orientation = orientation%orientation_limit;
        block = blocktype[orientation];
    }

    // 회전 거꾸로
    public void rotateBack(){
        orientation--;
        block = blocktype[orientation];
    }

    // 생성되고 화면에 세팅되면
    public void run(){
        while(alive) {
            try{
                // 시작하자마자는 위치 변경이 없으므로 먼저 sleep을 한다
                Thread.sleep(interval);
                if(alive) {
                    // Log.i("Run","y = "+y);
                    y++;
                    if (!collisionCheck()) {
                        //위치 변경후에는 다시 그리기를 요청한다
                        handler.sendEmptyMessage(Stage.REFRESH);
                    } else {
                        if(y == 1){
                            Log.i("Run","----- Quit -------");
                            handler.sendEmptyMessage(Stage.QUIT);
                        }else{
                            y--;
                            setBlockIntoStage();
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        try{
            interrupt();
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void setBlockIntoStage(){
        alive = false;
        for(int i=0 ;i < width ; i++){
            for(int j=0;j < width ; j++){
                // 현재 블럭의 셀의 값을 가져온다
                int cBlockValue = block[j][i];
                if( cBlockValue > 0){ // 현재 블럭 셀의 값이 0보다 클경우만 스테이지에 담는다
                    // 스테이지 셀에 블럭셀의 값을 담아준다
                    Stage.stageMap[y+j][x+i] = cBlockValue;
                }
            }
        }

        // 가중치 초기화
        pointWeight = 1;
        // 블럭을 스테이지에 입력한 후에 해당 블럭범위에 있는 스테이지 가로줄이 꽉찼으면 지워준다
        for(int i=y ; i < y+4 ; i++){
            // 전체 스테이지 height값보다 작을때만
            if(i < 20) {
                // 스테이지 맵에서 한줄식 꺼낸다
                int row[] = Stage.stageMap[i];
                int zeroCount = 0;
                // 각 로우의 셀에 0이 있는지 검사
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == 0) {
                        zeroCount++;
                    }
                }

                // 각로우의 셀에 0이 한개도 없으면
                if (zeroCount == 0) {
                    // 해당줄을 지운다 - 윗 줄을 아래로 가져온다
                    // jongkook 2016.10.19
                    Log.i("pointWeight","pointWeight="+pointWeight);
                    // 2016.10.19 가중치 2배
                    pointWeight = pointWeight * 2;

                    for (int m = i; m > 0; m--) {
                        for (int k = 1; k < row.length-1; k++) {
                            // Log.i("zeroCount","i="+i+", m="+m+", k="+k);
                            Stage.stageMap[m][k] = Stage.stageMap[m-1][k];
                        }
                    }
                }
            }
        }
        Stage.pointNum = pointWeight * 100;
        Log.i("BlockPoint","Stage.pointNum ------ "+Stage.pointNum);
//        msg.arg1 = Stage.pointNum;
//        handler.sendMessage(msg);
        Stage.blockGroup = null;
        handler.sendEmptyMessage(Stage.NEW_BLOCK);
    }

    public boolean collisionCheck(){
        boolean result = false;
        for(int i=0 ;i < width ; i++){
            for(int j=0;j < height ; j++){
                // 현재 블럭의 셀의 값을 가져온다
                int cBlockValue = block[j][i];
                if( cBlockValue > 0){ // 현재 블럭 셀의 값이 0보다 클경우만 충돌체크를 한다
                    // 이동한곳의 stage 셀값과 block 의 셀값을 더한다
                    int sum = cBlockValue + Stage.stageMap[y+j][x+i];
                    if(sum > cBlockValue){ // 두개 셀을 더한값이 블럭셀의 값보다 크면 충돌
                        return true;
                    }
                }
            }
        }
        return result;
    }
}