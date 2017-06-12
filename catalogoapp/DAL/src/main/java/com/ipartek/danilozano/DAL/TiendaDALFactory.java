package com.ipartek.danilozano.DAL;

public class TiendaDALFactory {
	
	
//	//Dal factory de productos usando dal antiguo
//	public static TiendaDAL getProductosDAL() {
//
//		return new TiendaDALColeccion();
//	}
//	//Dal factory de Usuarios usando dal antiguo
//	public static TiendaDAL getUsuariosDAL() {
//
//		return new TiendaDALColeccion();
//	}
	//Dal factory de Usuarios DAO
		public static TiendaDAOMySQL getUsuariosDAL() {
			 
			return new TiendaDAOMySQL();
		}
	
		//Dal factory de productos DAO
		 public static TiendaDAOMySQL getProductosDAL() {
		        
		        return new TiendaDAOMySQL();
		    }
		}