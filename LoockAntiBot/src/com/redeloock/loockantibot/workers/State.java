package com.redeloock.loockantibot.workers;

import java.util.HashMap;

import com.redeloock.loockantibot.utils.Data;

public class State {
	int nextJoinTime;
	boolean reducingTime;
	private int amountJoin;
	public static int maxAmountJoin;
	private static HashMap<String, State> states = new HashMap<>();

	public State(String state) {
		amountJoin = 0;
		reducingTime = false;
		nextJoinTime = 0;
		states.put(state, this);
	}

	public void Timer() {
		if (!reducingTime) {
			nextJoinTime = Data.tempoEntrarProximo;
			reducingTime = true;
		}
		Data.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Data.plugin, new Runnable() {
			public void run() {
				if (nextJoinTime > 0) {
					nextJoinTime -= 1;
					Timer();
				} else {
					reducingTime = false;
					Reset();
				}
			}
		}, 20);
	}

	public void Enter() {
		amountJoin += 1;
		Timer();
	}

	private void Reset() {
		amountJoin = 0;
	}

	public boolean getAllowed() {
		return amountJoin < maxAmountJoin;
	}

	public static State getState(String state) {
		if (states.containsKey(state))
			return states.get(state);
		else {
			State stateReturn = new State(state);
			return stateReturn;
		}
	}

	public int getTime() {
		return nextJoinTime;
	}
}
