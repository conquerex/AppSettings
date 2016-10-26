package com.example.jongkook.remote_retrofitwithokhttp;

import java.util.List;

public class RemoteData {
    private SeoulRoadNameInfo SeoulRoadNameInfo;

    public SeoulRoadNameInfo getSeoulRoadNameInfo ()
    {
        return SeoulRoadNameInfo;
    }

    public void setSeoulRoadNameInfo (SeoulRoadNameInfo SeoulRoadNameInfo)
    {
        this.SeoulRoadNameInfo = SeoulRoadNameInfo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SeoulRoadNameInfo = "+SeoulRoadNameInfo+"]";
    }

    public class SeoulRoadNameInfo
    {
        private RESULT RESULT;

        private String list_total_count;

        private List<Row> row;

        public RESULT getRESULT ()
        {
            return RESULT;
        }

        public void setRESULT (RESULT RESULT)
        {
            this.RESULT = RESULT;
        }

        public String getList_total_count ()
        {
            return list_total_count;
        }

        public void setList_total_count (String list_total_count)
        {
            this.list_total_count = list_total_count;
        }

        public List<Row> getRow ()
        {
            return row;
        }

        public void setRow (List<Row> row)
        {
            this.row = row;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [RESULT = "+RESULT+", list_total_count = "+list_total_count+", row = "+row+"]";
        }
    }


    public class Row
    {
        private String ROAD_WIDTH;

        private String ROAD_TYPE;

        private String ROAD_FUNC;

        private String ROAD_NM;

        private String CGG_DIV;

        private String ROAD_SCALE;

        public String getROAD_WIDTH ()
        {
            return ROAD_WIDTH;
        }

        public void setROAD_WIDTH (String ROAD_WIDTH)
        {
            this.ROAD_WIDTH = ROAD_WIDTH;
        }

        public String getROAD_TYPE ()
        {
            return ROAD_TYPE;
        }

        public void setROAD_TYPE (String ROAD_TYPE)
        {
            this.ROAD_TYPE = ROAD_TYPE;
        }

        public String getROAD_FUNC ()
        {
            return ROAD_FUNC;
        }

        public void setROAD_FUNC (String ROAD_FUNC)
        {
            this.ROAD_FUNC = ROAD_FUNC;
        }

        public String getROAD_NM ()
        {
            return ROAD_NM;
        }

        public void setROAD_NM (String ROAD_NM)
        {
            this.ROAD_NM = ROAD_NM;
        }

        public String getCGG_DIV ()
        {
            return CGG_DIV;
        }

        public void setCGG_DIV (String CGG_DIV)
        {
            this.CGG_DIV = CGG_DIV;
        }

        public String getROAD_SCALE ()
        {
            return ROAD_SCALE;
        }

        public void setROAD_SCALE (String ROAD_SCALE)
        {
            this.ROAD_SCALE = ROAD_SCALE;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ROAD_WIDTH = "+ROAD_WIDTH+", ROAD_TYPE = "+ROAD_TYPE+", ROAD_FUNC = "+ROAD_FUNC+", ROAD_NM = "+ROAD_NM+", CGG_DIV = "+CGG_DIV+", ROAD_SCALE = "+ROAD_SCALE+"]";
        }
    }


    public class RESULT
    {
        private String MESSAGE;

        private String CODE;

        public String getMESSAGE ()
        {
            return MESSAGE;
        }

        public void setMESSAGE (String MESSAGE)
        {
            this.MESSAGE = MESSAGE;
        }

        public String getCODE ()
        {
            return CODE;
        }

        public void setCODE (String CODE)
        {
            this.CODE = CODE;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [MESSAGE = "+MESSAGE+", CODE = "+CODE+"]";
        }
    }
}
