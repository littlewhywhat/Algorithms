package com.littlewhywhat.algorithms.schedule;

import java.util.Queue;

public interface Schedule<E extends Job> extends Queue<E> {
	int getTime();
	int resetTime();
}
