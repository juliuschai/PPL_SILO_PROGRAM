/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.time.LocalDate;

/**
 *
 * @author Julius
 */
public class SuratPembelian {

    public String nomorSP;
    public String nomorPO;
    public String namaSuplier;
    public LocalDate tanggalOrder;
    public LocalDate tanggalSelesai;
    public Status status;

    public void terimaSP() {
        this.status.terimaSP();
    }

    public void pendingSP() {
        this.status.pendingSP();
    }

    public class Status {

        State state;

        public Status(String status) {
            if (status.equals("new")) {
                this.state = new NewState();
            } else if (status.equals("pending")) {
                this.state = new PendingState();
            } else if (status.equals("completed")) {
                this.state = new CompletedState();
            } else {
                throw new UnsupportedOperationException("SP Status: " + status
                        + " undefined");
            }
        }
        
        private void setState(State newState) {
            this.state = newState;
        }

        public String toString() {
            return state.toString();
        }

        public void terimaSP() {
            state.terimaSP(this);
        }

        public void pendingSP() {
            state.pendingSP(this);
        }
 
    }

    interface State {

        String toString();

        void terimaSP(Status context);

        void pendingSP(Status context);
    }

    class NewState implements State {

        @Override
        public String toString() {
            return "new";
        }

        @Override
        public void terimaSP(Status context) {
            context.setState(new CompletedState());
        }

        @Override
        public void pendingSP(Status context) {
            context.setState(new PendingState());
        }

    }
        
    class CompletedState implements State {

        @Override
        public String toString() {
            return "completed";
        }

        @Override
        public void terimaSP(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for current state.");
        }

        @Override
        public void pendingSP(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for current state.");
        }

    }
        
    class PendingState implements State {

        @Override
        public String toString() {
            return "pending";
        }

        @Override
        public void terimaSP(Status context) {
            context.setState(new CompletedState());
        }

        @Override
        public void pendingSP(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for current state.");
        }
        
    }
}
