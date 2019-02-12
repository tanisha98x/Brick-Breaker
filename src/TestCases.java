import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

public class TestCases {



    public void runTestCase(int level, KeyCode code, Paddle myPaddle){
        if (code==KeyCode.PERIOD){
            TestCasePeriod(level, myPaddle);
        }
        if (code==KeyCode.COMMA){
            TestCaseComma(level);
        }
        if (code==KeyCode.BACK_SLASH){
            TestCaseBackSlash(level);
        }
    }



    private void TestCasePeriod(int level, Paddle myPadel){
        if (level==1){

        }
        if (level==2){

        }
        if (level==3){

        }
    }

    private void checkIfPaddleLengthIncreases(Paddle myPaddle){
        double width1= myPaddle.getView().getFitWidth();
        myPaddle.updateWidth(4, myPaddle);
        double width2=myPaddle.getView().getFitWidth();

        if (width1>width2){
            System.out.print("Passes the increase paddle length test");
        }
        System.out.println("Fails the increase paddle length test");
    }



    private void TestCaseComma(int level){
        if (level==1){

        }
        if (level==2){

        }
        if (level==3){

        }

    }

    private void TestCaseBackSlash(int level){
        if (level==1){

        }
        if (level==2){

        }
        if (level==3){

        }

    }




















}
