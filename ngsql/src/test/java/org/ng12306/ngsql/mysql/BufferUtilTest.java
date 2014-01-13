package org.ng12306.ngsql.mysql;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.Test;

public class BufferUtilTest {

	@Test
	public void testWriteUB2() {
		ByteBuffer buffer = ByteBuffer.allocate(100);
		BufferUtil.writeUB2(buffer, 20);
		int a;
	}

	@Test
	public void testWriteUB3() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteFloat() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteUB4() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteLength() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteWithNull() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteWithLengthByteBufferByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteWithLengthByteBufferByteArrayByte() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLengthLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLengthByteArray() {
		fail("Not yet implemented");
	}

}
