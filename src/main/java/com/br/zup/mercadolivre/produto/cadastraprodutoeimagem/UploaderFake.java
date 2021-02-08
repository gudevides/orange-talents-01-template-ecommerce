package com.br.zup.mercadolivre.produto.cadastraprodutoeimagem;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UploaderFake {

    /**
     *
     * @param imagens
     * @return links para imagens que foram salvas
     */
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(imagem -> "http://images.io/"+imagem.getOriginalFilename()).collect(Collectors.toSet());
    }
}
