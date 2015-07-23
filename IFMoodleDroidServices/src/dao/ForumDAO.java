package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import model.Forum;
import model.Mensagem;
import model.MensagemTopico;
import model.Topicos;
import factory.ConnectionFactory;

public class ForumDAO {
	
	public ArrayList<Forum> retornaForumsCurso(long cursoID) throws Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Forum forumTemp = null;
		ArrayList<Forum> listForums = new ArrayList<Forum>();
		boolean resultVazio = true;
		
		try{
			String query ="SELECT id , name from mdl_forum WHERE course = ? ";
			connection = ConnectionFactory.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, cursoID);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				resultVazio = false;
				forumTemp = new Forum();
				forumTemp.setId(resultSet.getInt("id"));
				forumTemp.setNome(resultSet.getString("name"));
				
				listForums.add(forumTemp);
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(PostagemDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException ex) {
				Logger.getLogger(PostagemDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		
		if(resultVazio){
			return null;
		}
		
		return listForums;
	}
	
	public ArrayList<Topicos> retornaTopicosForum(long idForum) throws Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Topicos topicosTemp = null;
		ArrayList<Topicos> listTopicos = new ArrayList<Topicos>();
		boolean resultVazio = true;
		
		try{
			String query ="SELECT id ,course, forum, name from mdl_forum_discussions WHERE forum = ? ";
			connection = ConnectionFactory.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, idForum);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				resultVazio = false;
				topicosTemp = new Topicos();
				topicosTemp.setIdTopico(resultSet.getInt("id"));
				topicosTemp.setIdCurso(resultSet.getInt("course"));
				topicosTemp.setIdForum(resultSet.getInt("forum"));
				topicosTemp.setNomeTopico(resultSet.getString("name"));
				
				listTopicos.add(topicosTemp);
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(PostagemDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException ex) {
				Logger.getLogger(PostagemDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		
		if(resultVazio){
			return null;
		}
		
		return listTopicos;
	}
	
	public ArrayList<Forum> retornaForunsSemanas(long semanaId) throws Exception{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Forum forumTemp = null;
		ArrayList<Forum> listForunsSemanas = new ArrayList<Forum>();
		boolean resultVazio = true;
		
		try{
			String query ="SELECT id, course, name, intro FROM mdl_forum where id = (SELECT instance FROM mdl_course_modules WHERE module = 9 AND section = ?)";
			connection = ConnectionFactory.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, semanaId);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				resultVazio = false;
				forumTemp = new Forum();
				forumTemp.setId(resultSet.getInt("id"));
				forumTemp.setNome(resultSet.getString("name"));
				forumTemp.setCourse(resultSet.getString("course"));
				forumTemp.setIntro(resultSet.getString("intro"));
				
				listForunsSemanas.add(forumTemp);
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(PostagemDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException ex) {
				Logger.getLogger(PostagemDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		
		if(resultVazio){
			return null;
		}
		
		return listForunsSemanas;

	}

	public MensagemTopico responderForumPost(String login, String senha, String flagEncriptacao,
			String userId, String parent, String discussion, String subject, String message) throws Exception {
		Connection connection = null;
		int retorno_statement = 0;
		PreparedStatement stmt = null;

		MensagemTopico temp = null;
		try {
			String query = "INSERT INTO mdl_forum_posts(discussion, parent, userid, created, modified, subject, message) VALUES (?,?,?,now(),now(),?,?)";
			connection = ConnectionFactory.getInstance().getConnection();

			stmt = connection.prepareStatement(query);

			stmt.setLong(1, Long.parseLong(discussion));
			stmt.setLong(2, Long.parseLong(parent));
			stmt.setLong(3, Long.parseLong(userId));
			stmt.setString(4, subject);
			stmt.setString(5, message);
			
			retorno_statement = stmt.executeUpdate();

			if (retorno_statement != 0) {// se der tudo certo 
				temp = new MensagemTopico(Long.parseLong(discussion), Long.parseLong(parent), Long.parseLong(userId), subject, message);
			}

		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {

				if (retorno_statement != 0) {
					stmt.close();
				}

			} catch (SQLException ex) {
				Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}

		}
		return temp;

	}


}
