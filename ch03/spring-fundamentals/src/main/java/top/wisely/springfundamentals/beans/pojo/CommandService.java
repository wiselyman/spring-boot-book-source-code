package top.wisely.springfundamentals.beans.pojo;

public class CommandService {
    private String listCommand;

    public CommandService(String listCommand) {
        this.listCommand = listCommand;
    }

    public void list(){
        System.out.println("当前系统下列表命令是：" + listCommand);
    }
}
