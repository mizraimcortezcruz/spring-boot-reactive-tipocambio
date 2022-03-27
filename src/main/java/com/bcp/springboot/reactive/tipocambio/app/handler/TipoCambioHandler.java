package com.bcp.springboot.reactive.tipocambio.app.handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.bcp.springboot.reactive.tipocambio.app.service.TipoCambioReactiveServiceImpl;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.BodyInserters.*;
import com.bcp.springboot.reactive.tipocambio.app.entity.TipoCambio;
@Component
public class TipoCambioHandler {
	@Autowired
	private TipoCambioReactiveServiceImpl tipoCambioReactiveServiceImpl;
	@SuppressWarnings("deprecation")
	public Mono<ServerResponse> listar(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(tipoCambioReactiveServiceImpl.findAll(), TipoCambio.class);
	}
	@SuppressWarnings("deprecation")
	public Mono<ServerResponse> ver(ServerRequest request){
		String id = request.pathVariable("id");
		Long codigo = Long.parseLong(id);
		return tipoCambioReactiveServiceImpl.customGetById(codigo).flatMap( p -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(fromObject(p)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}