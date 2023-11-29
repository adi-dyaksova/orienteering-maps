package com.orienteering.maps.api;

import com.orienteering.maps.model.Map;
import com.orienteering.maps.model.MapSearchCriteria;
import com.orienteering.maps.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("api/v1/map")
@RestController
@CrossOrigin("*") //TODO
public class MapController {
    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService){
        this.mapService=mapService;
    }

    @PostMapping
    public void insertMap(@Valid @NonNull @RequestBody Map map) throws IOException {
        this.mapService.insertMap(map);
    }

    @GetMapping
    public List<Map> getAllMaps(){
       return this.mapService.getAllMaps();
    }


    @GetMapping(path = "{id}")
    public Map getMapById( @PathVariable("id") Integer id){
        return mapService.getMapById(id)
                .orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updateMapById(@PathVariable("id") Integer id,@Valid @NonNull @RequestBody Map newMap){
        mapService.updateMapById(id,newMap);
    }

    @PostMapping(path = "/search")
    public List<Map> getFilteredMaps( @Valid @NonNull @RequestBody MapSearchCriteria mapSearchCriteria){
        return this.mapService.getFilteredMaps(mapSearchCriteria);
    }

    @PostMapping(path = "/{id}/uploadMapFile")
    public void uploadMapFile(@PathVariable("id") Integer mapId, @RequestParam("file") MultipartFile file) throws IOException {
        mapService.uploadMapFile(mapId,file);
    }

    @GetMapping(path = "/{id}/getMapFile")
    public ResponseEntity<byte[]> getMapFile(@PathVariable("id") Integer mapId) throws IOException {
       byte[] data= mapService.getMapFile(mapId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_PDF).body(data);
    }

}
