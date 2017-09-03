package com.base.data.access.alyer.proto;

public class Proto {
    public byte[] packet = null;
    public int offset = 0;

    public Proto(byte[] packet) {
        this.packet = packet;
    }

    public boolean has_remaining_data() {
        return this.packet.length - this.offset > 0;
    }

    public static byte[] build_fixed_int(int size, long value) {
        byte[] packet = new byte[size];

        if(size == 8) {
            packet[0] = (byte)(value & 0xFF);
            packet[1] = (byte)((value >> 8) & 0xFF);
            packet[2] = (byte)((value >> 16) & 0XFF);
            packet[3] = (byte)((value) >> 24 & 0XFF);
            packet[4] = (byte)((value >> 32) & 0XFF);
            packet[5] = (byte)((value >> 40) & 0XFF);
            packet[6] = (byte)((value >> 48) & 0XFF);
            packet[7] = (byte)((value >> 56) & 0XFF);
        } else if(size == 6) {
            packet[0] = (byte)(value & 0xFF);
            packet[1] = (byte)((value >> 8) & 0xFF);
            packet[2] = (byte)((value >> 16) & 0XFF);
            packet[3] = (byte)((value) >> 24 & 0XFF);
            packet[4] = (byte)((value >> 32) & 0XFF);
            packet[5] = (byte)((value >> 40) & 0XFF);
        } else if(size == 4) {
            packet[0] = (byte)(value & 0xFF);
            packet[1] = (byte)((value >> 8) & 0xFF);
            packet[2] = (byte)((value >> 16) & 0XFF);
            packet[3] = (byte)((value) >> 24 & 0XFF);
        } else if(size == 3) {
            packet[0] = (byte)(value & 0xFF);
            packet[1] = (byte)((value >> 8) & 0xFF);
            packet[2] = (byte)((value >> 16) & 0XFF);
        } else if(size == 2) {
            packet[0] = (byte)(value & 0xFF);
            packet[1] = (byte)((value >> 8) & 0xFF);
        } else if(size == 1) {

        }
    }



    public static long get_fixed_int(byte[] bytes) {
        long value = 0;
        for(int i = bytes.length - 1; i > 0; i--) {
            value |= bytes[i] & 0XFF;
            value <<= 8;
        }
        value |= bytes[0] & 0XFF;
        return value;
    }

    public void get_filter(int size) {
        this.offset += size;
    }

    public long get_lenenc_int() {
        int size = 0;
        int firstint = this.packet[offset] & 0XFF;
        // 1 byte int
        if(firstint < 251) {
            size = 1;
        }
    }
}
