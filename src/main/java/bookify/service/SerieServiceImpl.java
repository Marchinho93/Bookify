package bookify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookify.model.Book;
import bookify.model.Serie;
import bookify.repository.SerieDao;

@Service
public class SerieServiceImpl implements SerieService{
	
	@Autowired
	SerieDao serieDao;
	
	public Serie create(Serie serie){
		serieDao.create(serie);
		return serie;
	}
	
	public Serie create(String name){
		Serie serie = new Serie(name);
		serieDao.create(serie);
		return serie;
	}

	public Serie findByName(String name){
		return serieDao.findByName(name);
	}

	public Serie findByBook(long code){
		return serieDao.findByBook(code);
	}

	public List<Serie> findAll(){
		return serieDao.findAll();
	}

	public Serie update(Serie serie){
		serieDao.update(serie);
		return serie;
	}

	public Serie delete(Serie serie){
		serieDao.delete(serie);
		return serie;
	}

}
