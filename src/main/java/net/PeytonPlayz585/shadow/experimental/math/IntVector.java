package net.PeytonPlayz585.shadow.experimental.math;

public class IntVector implements Cloneable {

	protected int m_blocksize;
	protected int m_map[];
	protected int m_firstFree = 0;
	protected int m_mapSize;

	public IntVector() {
		m_blocksize = 32;
		m_mapSize = m_blocksize;
		m_map = new int[m_blocksize];
	}

	public IntVector(int blocksize) {
		m_blocksize = blocksize;
		m_mapSize = blocksize;
		m_map = new int[blocksize];
	}

	public IntVector(int blocksize, int increaseSize) {
		m_blocksize = increaseSize;
		m_mapSize = blocksize;
		m_map = new int[blocksize];
	}

	public IntVector(IntVector v) {
		m_map = new int[v.m_mapSize];
		m_mapSize = v.m_mapSize;
		m_firstFree = v.m_firstFree;
		m_blocksize = v.m_blocksize;
		System.arraycopy(v.m_map, 0, m_map, 0, m_firstFree);
	}

	public final int size() {
		return m_firstFree;
	}

	public final void setSize(int sz) {
		m_firstFree = sz;
	}

	public final void addElement(int value) {
		if ((m_firstFree + 1) >= m_mapSize) {
			m_mapSize += m_blocksize;
			int newMap[] = new int[m_mapSize];
			System.arraycopy(m_map, 0, newMap, 0, m_firstFree + 1);
			m_map = newMap;
		}

		m_map[m_firstFree] = value;
		m_firstFree++;
	}

	public final void addElements(int value, int numberOfElements) {
		if ((m_firstFree + numberOfElements) >= m_mapSize) {
			m_mapSize += (m_blocksize + numberOfElements);
			int newMap[] = new int[m_mapSize];
			System.arraycopy(m_map, 0, newMap, 0, m_firstFree + 1);
			m_map = newMap;
		}

		for (int i = 0; i < numberOfElements; i++) {
			m_map[m_firstFree] = value;
			m_firstFree++;
		}
	}

	public final void addElements(int numberOfElements) {
		if ((m_firstFree + numberOfElements) >= m_mapSize) {
			m_mapSize += (m_blocksize + numberOfElements);
			int newMap[] = new int[m_mapSize];
			System.arraycopy(m_map, 0, newMap, 0, m_firstFree + 1);
			m_map = newMap;
		}

		m_firstFree += numberOfElements;
	}

	public final void insertElementAt(int value, int at) {
		if ((m_firstFree + 1) >= m_mapSize) {
			m_mapSize += m_blocksize;
			int newMap[] = new int[m_mapSize];
			System.arraycopy(m_map, 0, newMap, 0, m_firstFree + 1);
			m_map = newMap;
		}

		if (at <= (m_firstFree - 1)) {
			System.arraycopy(m_map, at, m_map, at + 1, m_firstFree - at);
		}

		m_map[at] = value;
		m_firstFree++;
	}

	public final void removeAllElements() {
		for (int i = 0; i < m_firstFree; i++) {
			m_map[i] = java.lang.Integer.MIN_VALUE;
		}

		m_firstFree = 0;
	}

	public final boolean removeElement(int s) {
		for (int i = 0; i < m_firstFree; i++) {
			if (m_map[i] == s) {
				if ((i + 1) < m_firstFree) {
					System.arraycopy(m_map, i + 1, m_map, i - 1, m_firstFree - i);
				} else {
					m_map[i] = java.lang.Integer.MIN_VALUE;
				}

				m_firstFree--;
				return true;
			}
		}

		return false;
	}

	public final void removeElementAt(int i) {

		if (i > m_firstFree) {
			System.arraycopy(m_map, i + 1, m_map, i, m_firstFree);
		} else { 
			m_map[i] = java.lang.Integer.MIN_VALUE;
		}
		
		m_firstFree--;
	}

	public final void setElementAt(int value, int index) {
		m_map[index] = value;
	}

	public final int elementAt(int i) {
		return m_map[i];
	}

	public final boolean contains(int s) {
		for (int i = 0; i < m_firstFree; i++) {
			if (m_map[i] == s)
				return true;
		}

		return false;
	}

	public final int indexOf(int elem, int index) {
		for (int i = index; i < m_firstFree; i++) {
			if (m_map[i] == elem)
				return i;
		}

		return java.lang.Integer.MIN_VALUE;
	}

	public final int indexOf(int elem) {
		for (int i = 0; i < m_firstFree; i++) {
			if (m_map[i] == elem)
				return i;
		}

		return java.lang.Integer.MIN_VALUE;
	}

	public final int lastIndexOf(int elem) {
		for (int i = (m_firstFree - 1); i >= 0; i--) {
			if (m_map[i] == elem)
				return i;
		}

		return java.lang.Integer.MIN_VALUE;
	}

	@Override
	public Object clone() {
		return new IntVector(this);
	}
}