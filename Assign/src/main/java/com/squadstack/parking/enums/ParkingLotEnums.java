package com.squadstack.parking.enums;

import com.squadstack.parking.filehandler.IFileHandler;
import com.squadstack.parking.filehandler.TextFileHandler;

public class ParkingLotEnums {

    private ParkingLotEnums() {
        // marking as private
    }

    public enum SlotState {
        FREE,
        OCCUPIED,
        UNDER_MAINTAINANCE,
    }

    public enum LotState{
        ACTIVE,
        NOT_ACTIVE,
        ABANDONED
    }

    public enum ArgsType{
        COMMAND,
        OPTION
    }

    public enum FileType{
        TXT{
            @Override
            public IFileHandler getFileHandlerInstance() {
                return new TextFileHandler();
            }
        };

        public abstract IFileHandler getFileHandlerInstance();
    }
}
