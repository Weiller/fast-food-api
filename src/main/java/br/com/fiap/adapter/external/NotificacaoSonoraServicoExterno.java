package br.com.fiap.adapter.external;

import br.com.fiap.core.ports.NotificacaoSonoraPort;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoSonoraServicoExterno implements NotificacaoSonoraPort {

        @Override
        public void notificar() {
            System.out.println("Notificação sonora enviada");
        }

}
