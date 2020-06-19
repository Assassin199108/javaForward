package com.wangwei.basic.decompiler;

public class DeCompilerTestDTO {

    private int a;

    private int b;

    @Override
    public String toString() {
        return "DeCompilerTestDTO{" +
                "a=" + a +
                '}';
    }

    public static void main(String[] args) {
        DeCompilerTestDTO deCompilerTestDTO = new DeCompilerTestDTO();
        deCompilerTestDTO.a = 11;
        deCompilerTestDTO.b = 10;

        deCompilerTestDTO.testTransfer(deCompilerTestDTO);

        while (true){

        }

    }

    public void testTransfer(DeCompilerTestDTO deCompilerTestDTO) {
        int c = deCompilerTestDTO.a;
        System.out.println(c);
        System.out.println(this.returnTransfer(deCompilerTestDTO));
    }

    public DeCompilerTestDTO returnTransfer(DeCompilerTestDTO deCompilerTestDTO) {
        return deCompilerTestDTO;
    }
}
