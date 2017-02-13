package br.com.thiengo.blogapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import br.com.thiengo.blogapp.R;
import br.com.thiengo.blogapp.presenter.PresenterMain;

public class MainActivity extends AppCompatActivity {
    /*
     * CÓDIGO INTEIRO ALEATÓRIO PARA POSTERIOR
     * VERIFICAÇÃO NO ON-ACTIVITY-RESULT
     * */
    public static final int APP_REQUEST_CODE = 665;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* VERIFICA SE O USUÁRIO JÁ ESTÁ CONECTADO */
        if (AccountKit.getCurrentAccessToken() != null) {
            startActivity(new Intent(this, PostsActivity.class));
            finish(); /* REMOVE A ACTIVITY ATUAL DA PILHA DE ACTIVITIES */
        }
        else{
            onLoginEmail();
        }
    }

    public void onLoginEmail() {
        /*
         * DEFINIÇÃO COMPLETA PARA QUE SEJA APRESENTADA
         * UMA ACTIVITY DE LOGIN COM SOLICITAÇÃO DE EMAIL
         * */
        Intent intent = new Intent(this, AccountKitActivity.class);

        AccountKitConfiguration
            .AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration
                        .AccountKitConfigurationBuilder(
                            LoginType.EMAIL,
                            AccountKitActivity.ResponseType.TOKEN );

        intent.putExtra(
            AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
            configurationBuilder.build() );

        startActivityForResult( intent, APP_REQUEST_CODE );
    }


    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == APP_REQUEST_CODE) {

            /* ACESSANDO O RESULTADO DA ACTIVITY DE LOGIN */
            AccountKitLoginResult loginResult = data.getParcelableExtra(
                                                    AccountKitLoginResult.RESULT_KEY );

            if (loginResult.getError() != null) {
                String mensagem = loginResult.getError().getErrorType().getMessage();
                Toast.makeText( this, mensagem, Toast.LENGTH_LONG ).show();
            }
            else if (loginResult.wasCancelled()) {
                /*
                 * CASO O BACK ARROW TENHA SIDO PRESSIONADO,
                 * FECHAMOS A ACTIVITY DE LOGIN E SAÍMOS DO APLICATIVO
                 * */
                finish();
            }
            else {
                /* TUDO CERTO, VAMOS A ACTIVITY DE POSTS DO BLOG */
                startActivity(new Intent(this, PostsActivity.class));
                finish();
            }
        }
    }
}


