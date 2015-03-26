package com.su.doku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import android.widget.Button;

public class SaveReadState {

	int puzzleScale = 9;
	int queueSize = 3;
	int[][] puzzle = new int[puzzleScale][puzzleScale];
	int[][] currentMatrix = new int[puzzleScale][puzzleScale];
	int[] queue = new int[queueSize];
	double time = 0;
	String filePath = "/SuDoKu/CurrentState.txt";

	public void SaveState(int[][] puzzle, int[][] currentMatrix, int[] queue) {
		this.puzzle = puzzle;
		this.currentMatrix = currentMatrix;
		this.queue = queue;
		WriteFile();
	}

	private void WriteFile() {
		BufferedWriter writer = null;
		File file = null;

		try {
			file = new File(filePath);
			file.createNewFile();
			// ���I�s�X�榡�A�H�KŪ���ɤ���r�Ų��`
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true), "UTF-8"));

			// �x�s���ׯx�}
			for (int i = 0; i < puzzleScale; i++) {
				for (int j = 0; j < puzzleScale; j++) {
					writer.append(puzzle[i][j] + " ");
				}
				writer.newLine();
			}
			writer.flush(); // �����g�J�w�s�������e

			// �x�s�ثe��g���x�}
			for (int i = 0; i < puzzleScale; i++) {
				for (int j = 0; j < puzzleScale; j++) {
					writer.append(currentMatrix[i][j] + " ");
				}
				writer.newLine();
			}
			writer.flush();

			// �x�s�ثequeue�̭����Ʀr
			for (int i = 0; i < queueSize; i++) {
				writer.append(queue[i] + " ");
			}
			writer.newLine();
			writer.flush();

			// �x�s�ثe�ɶ�
			writer.append(String.valueOf(time));
			writer.newLine();
			writer.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void ReadState() {
		File file = null;
		Scanner reader = null;
		try {
			file = new File(filePath);
			reader = new Scanner(file);
			// Ū�����ׯx�}
			for (int i = 0; i < puzzleScale; i++) {
				for (int j = 0; j < puzzleScale; j++) {
					puzzle[i][j] = reader.nextInt();
				}
			}
			// Ū���ثe��g���x�}
			for (int i = 0; i < puzzleScale; i++) {
				for (int j = 0; j < puzzleScale; j++) {
					currentMatrix[i][j] = reader.nextInt();
				}
			}
			// Ū���ثequeue�̭����Ʀr
			for (int i = 0; i < queueSize; i++) {
				queue[i] = reader.nextInt();
			}
			// �x�s�ثe�ɶ�
			time = reader.nextDouble();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}

	public int[][] getAnswer() {
		return puzzle;
	}

	public int[][] getUserMatrix() {
		return currentMatrix;
	}

	public double getTime() {
		return time;
	}

	public int[] getQueue() {
		return queue;
	}
}
