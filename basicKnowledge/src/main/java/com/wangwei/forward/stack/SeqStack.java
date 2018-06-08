package com.wangwei.forward.stack;

import com.wangwei.forward.stack.exception.EmptyStackException;

import java.io.Serializable;

public class SeqStack<T> implements Stack<T>,Serializable {

    private static final long serialVersionUID = -6754476204885772228L;

    public SeqStack() {
        array = (T[]) new Object[capacity];
    }

    public SeqStack(int capacity) {
        array = (T[]) new Object[capacity];
    }

    /**
     * 栈顶指针,-1代表空栈
     */
    private int top = -1;

    /**
     * 容量大小默认为10
     */
    private int capacity = 10;

    /**
     * 存放元素的数组
     */
    private T[] array;

    private int size;

    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    /**
     * 添加元素,从栈顶(数组尾部)插入
     * @param data T
     */
    @Override
    public void push(T data) {
        if (size > capacity){
            throw new ArrayIndexOutOfBoundsException("栈已满");
        }
        if (size == capacity){
            //扩容
            ensureCapacity(capacity*2);
        }
        array[size++] = data;
        top++;
    }

    /**
     * 扩容的方法
     * @param capacity int
     */
    private void ensureCapacity(int capacity){
        if (capacity < this.capacity){
            return;
        }

        T[] old = array;
        array = (T[]) new Object[capacity];
        System.arraycopy(old,0,array,0,capacity);
    }

    /**
     * 获取栈顶元素的值,不删除
     * @return T
     */
    @Override
    public T peek() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return array[top];
    }

    /**
     * 从栈顶(顺序表尾部)删除
     * @return T
     */
    @Override
    public T pop() {
        if (isEmpty()){
            throw new EmptyStackException();
        }

        top--;
        size--;
        T t = array[size];
        array[size] = null;

        return t;
    }

    public static void main(String[] args){
        SeqStack<String> stringSeqStack = new SeqStack<>();
        stringSeqStack.push("a");

        System.out.println("Size: "+stringSeqStack.getSize());
        System.out.println("Pop: "+stringSeqStack.top);

        for (int i = 0; i < stringSeqStack.size; i++) {
            System.out.println(stringSeqStack.peek());
        }
        System.out.println(String.format("Peek后的剩余的个数 %d",stringSeqStack.size));

        for (int i = 0; i < stringSeqStack.size; i++) {
            System.out.println(stringSeqStack.pop());
        }

        System.out.println(String.format("剩余的个数 %d",stringSeqStack.size));
    }
}
