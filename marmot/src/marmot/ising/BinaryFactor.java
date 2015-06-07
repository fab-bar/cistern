package marmot.ising;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryFactor extends Factor {

	private int size1;
	private int size2;
	protected double[][] potential;
	
	private List<Integer> features;

	
	// first variable id
	private int i;
	// second variable id
	private int j;
	
	public BinaryFactor(int size1, int size2, int i, int j) {
		this.setSize1(size1);
		this.setSize2(size2);
				
		this.setPotential(new double[this.size1][this.size2]);
		
		this.setFeatures(new LinkedList<Integer>());
		
		for (int n = 0; n < this.size1; ++n) {
			for (int m = 0; m < this.size2; ++m) {
				this.potential[n][m] = 1.0;
			}
		}
		
		
		this.setI(i);
		this.setJ(j);
		
		this.setNeighbors(new ArrayList<Variable>());
		
		this.setMessageIds(new ArrayList<Integer>());
		this.setMessages(new ArrayList<Message>());
	}
	

	@Override
	public void passMessage() {
		// outgoing messages
		Message m1_out = this.messages.get(0);
		Message m2_out = this.messages.get(1);
		
		// incoming messages
		
		Message m1_in = this.neighbors.get(0).getMessages().get(this.messageIds.get(0));
		Message m2_in = this.neighbors.get(1).getMessages().get(this.messageIds.get(1));
		
		// zero out
		m1_out.toZeros();
		m2_out.toZeros();
		
		// pass messages
		for (int n = 0; n < this.size1; ++n) {
			for (int m = 0; m < this.size2; ++m) {
				m2_out.measure[m] += this.potential[n][m] * m1_in.measure[n];
				m1_out.measure[n] += this.potential[n][m] * m2_in.measure[m];
			}
		}
	}

	@Override
	public void renormalize() {
		double Z = 0.0;
		for (int i = 0; i < this.size1; ++i) {
			for (int j = 0; j < this.size2; ++j) {
				Z += this.potential[i][j];
			}
		}
		System.out.println(Arrays.toString(this.potential));
		for (int i = 0; i < this.size1; ++i) {
			for (int j = 0; j < this.size2; ++j) {
				this.potential[i][j] /= Z;
			}
		}
		System.out.println(Arrays.toString(this.potential));

	}
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}


	public int getSize1() {
		return size1;
	}


	public void setSize1(int size1) {
		this.size1 = size1;
	}


	public int getSize2() {
		return size2;
	}


	public void setSize2(int size2) {
		this.size2 = size2;
	}


	public double[][] getPotential() {
		return potential;
	}


	public void setPotential(double[][] potential) {
		this.potential = potential;
	}
	
	public void setPotential(int n, int m, double value) {
		this.potential[n][m] = value;
	}


	public List<Integer> getFeatures() {
		return features;
	}


	public void setFeatures(List<Integer> features) {
		this.features = features;
	}


}
